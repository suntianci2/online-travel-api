package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.ScenicCategoryMapper;
import cn.kmbeast.mapper.ScenicMapper;
import cn.kmbeast.mapper.ScenicTicketMapper;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.pojo.entity.ScenicTicket;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.ScenicTicketVO;
import cn.kmbeast.service.ScenicCategoryService;
import cn.kmbeast.service.ScenicTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScenicTicketServiceImpl implements ScenicTicketService {

    @Autowired
    private ScenicTicketMapper scenicTicketMapper;

    @Autowired
    private VendorMapper vendorMapper;


    /**
     * 增加门票
     *
     * @param scenicTicket 新增的信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(ScenicTicket scenicTicket) {
        // 如果状态为null，设置状态为不可用
        if(scenicTicket.getUseStatus() == null){
            scenicTicket.setUseStatus(false);
        }
        scenicTicket.setCreateTime(LocalDateTime.now());
        int row = scenicTicketMapper.save(scenicTicket);
        if (row > 0) {
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    /**
     * 删除门票
     *
     * @param ids 待删除的门票的id列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicTicketMapper.batchDelete(ids);
        if (row > 0) {
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败");
    }

    /**
     * 更新门票信息
     *
     * @param scenicTicket 待更新的门票信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(ScenicTicket scenicTicket) {
        int row = scenicTicketMapper.update(scenicTicket);
        if (row > 0) {
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    /**
     * 查询门票信息
     *
     * @param scenicTicketQueryDto 封装查询信息的类
     * @return Result<Void>
     */
    @Override
    public Result<List<ScenicTicketVO>> query(ScenicTicketQueryDto scenicTicketQueryDto) {
        List<ScenicTicketVO> result = scenicTicketMapper.query(scenicTicketQueryDto);
        int total = scenicTicketMapper.queryTotal(scenicTicketQueryDto);
        return ApiResult.success(result, total);
    }

    // 查询指定服务商的门票信息
    @Override
    public Result<List<ScenicTicketVO>> queryTicketByVendor(ScenicTicketQueryDto scenicTicketQueryDto) {
        // 首先获取当前操作用户的id
        Integer userId = LocalThreadHolder.getUserId();
        // 根据用户id查询此用户关联的服务商
        Vendor vendor4Query = Vendor.builder().userId(userId).build();
        Vendor vendorIsExist = vendorMapper.getByActive(vendor4Query);
        if(vendorIsExist == null){
            return ApiResult.error("当前用户不存在相关服务商！");
        }

        // 查询该服务商的所有景点门票信息
        scenicTicketQueryDto.setVendorId(vendorIsExist.getId());
        List<ScenicTicketVO> result = scenicTicketMapper.query(scenicTicketQueryDto);
        int total = scenicTicketMapper.queryTotal(scenicTicketQueryDto);
        return ApiResult.success(result, total);

    }
}
