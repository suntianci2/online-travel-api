package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.ScenicMapper;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.ScenicVO;
import cn.kmbeast.pojo.vo.SelectedVO;
import cn.kmbeast.service.ScenicService;
import cn.kmbeast.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScenicServiceImpl implements ScenicService {

    @Autowired
    private ScenicMapper scenicMapper;
    @Autowired
    private VendorMapper vendorMapper;

    // 增加景点
    @Override
    public Result<Void> save(Scenic scenic) {
        // 判断当前景点名称是否已经存在
        Scenic tempScenic = Scenic.builder().name(scenic.getName()).build();
        Scenic isExist = scenicMapper.getByActive(tempScenic);
        if(isExist != null){
            return ApiResult.error("当前景点名已经存在！");
        }

        // 如果服务商为null，设置服务商为当前用户所对应的服务商。
        if(scenic.getVendorId() == null){
            // TODO: 使用**用户身份支持器**取得当前操作者的用户id，并且设置上查询参数
            Vendor nowvendor = Vendor.builder().userId(LocalThreadHolder.getUserId()).build();
            // 首先根据用户id查询当前用户的服务商
            Vendor vendorIsExist = vendorMapper.getByActive(nowvendor);
            // 如果服务商不为空，则设置为当前用户服务商。如果为空。则不变
            if(vendorIsExist != null){
                scenic.setVendorId(vendorIsExist.getId());
            }

        }

        // 如果状态为null，设置状态为false
        if(scenic.getStatus() == null){
            scenic.setStatus(false);
        }

        // 设置当前时间为创建时间
        scenic.setCreateTime(LocalDateTime.now());
        int row = scenicMapper.save(scenic);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");

    }

    // 批量删除景点
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改景点
    @Override
    public Result<Void> update(Scenic scenic) {
        int row = scenicMapper.update(scenic);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询景点
    @Override
    public Result<List<ScenicVO>> query(ScenicQueryDto scenicQueryDto) {
        // 查询分页结果
        List<ScenicVO> data = scenicMapper.query(scenicQueryDto);

        // 查询分页总数
        int total = scenicMapper.queryTotal(scenicQueryDto);
        return ApiResult.success(data, total);
    }

    // 根据服务商id查询当前服务商的景点信息
    @Override
    public Result<List<ScenicVO>> queryVendorScenic(ScenicQueryDto scenicQueryDto) {
        // TODO: 使用**用户身份支持器**取得当前操作者的用户id，并且设置上查询参数
        Vendor nowvendor = Vendor.builder().userId(LocalThreadHolder.getUserId()).build();

        // 首先根据用户id查询当前用户的服务商
        Vendor vendorIsExist = vendorMapper.getByActive(nowvendor);
        if(vendorIsExist == null){
            return ApiResult.error("不存在相应的服务商！");
        }
        // 然后根据服务商查询该服务商所有的景点
        scenicQueryDto.setVendorId(vendorIsExist.getId());
        // 查询分页结果
        List<ScenicVO> data = scenicMapper.query(scenicQueryDto);

        // 查询分页总数
        int total = scenicMapper.queryTotal(scenicQueryDto);
        return ApiResult.success(data, total);
    }

    @Override
    public Result<List<SelectedVO>> querySelectedScenic() {
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        // 只能查可用状态的景点信息
        scenicQueryDto.setStatus(true);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        // 再次封装
        List<SelectedVO> selectedScenicList = scenicVOS.stream()
                .map(scenicVO -> new SelectedVO(
                        scenicVO.getId(),
                        scenicVO.getName()
                )).collect(Collectors.toList());
        return ApiResult.success(selectedScenicList);
    }

    // 浏览操作
    @Override
    public Result<Void> viewOperation(Integer scenicId) {
        // 第一步，根据景点ID，查询回来景点的信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.success();
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String viewIds = scenicVO.getViewIds();
        // 已经存在，不需要处理了
        if (TextUtils.exitId(viewIds, LocalThreadHolder.getUserId())) {
            return ApiResult.success();
        }
        String newViewIds = TextUtils.join(viewIds, LocalThreadHolder.getUserId());
        // 填充新的信息
        Scenic scenic = new Scenic();
        scenic.setId(scenicId);
        scenic.setViewIds(newViewIds);
        // 修改
        scenicMapper.update(scenic);
        return ApiResult.success();
    }

    // 收藏操作 --- 收藏跟取消收藏是对立的
    @Override
    public Result<Void> saveOperation(Integer scenicId) {
        // 第一步，根据景点ID，查询回来景点的信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.success();
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String saveIds = scenicVO.getSaveIds();
        Scenic scenic = new Scenic();
        scenic.setId(scenicVO.getId());
        Integer userId = LocalThreadHolder.getUserId();
        // 最后都是更新，只是对于收藏用户ID列表（saveIds）字段的处理不一样
        // 实现方式1
        scenic.setSaveIds(
                TextUtils.exitId(saveIds, userId) ?
                        TextUtils.split(saveIds, userId) :
                        TextUtils.join(saveIds, userId)
        );
        // 实现方式2
        // 取消收藏操作
//        if (TextUtils.exitId(saveIds, LocalThreadHolder.getUserId())) {
//            scenic.setSaveIds(TextUtils.split(saveIds, userId));
//        } else { // 收藏操作
//            scenic.setSaveIds(TextUtils.join(saveIds, userId));
//        }
        // 更新字段
        scenicMapper.updateSaveIds(scenic);
        return ApiResult.success(TextUtils.exitId(saveIds, userId) ? "取消收藏成功" : "收藏成功");
    }

    // 查询用户收藏的景点信息
    @Override
    public Result<List<ScenicVO>> querySave() {
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        // 设置用户ID
        Integer userId = LocalThreadHolder.getUserId();
        scenicQueryDto.setSaveIds(String.valueOf(userId));
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        return ApiResult.success(scenicVOS);
    }


    // 查询用户的收藏景点状况
    @Override
    public Result<Boolean> saveStatus(Integer scenicId) {
        // 第一步，根据景点ID，查询回来景点的信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.success();
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String saveIds = scenicVO.getSaveIds();
        return ApiResult.success(TextUtils.exitId(saveIds, LocalThreadHolder.getUserId()));
    }


}
