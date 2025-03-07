package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.ScenicStrategyMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicStrategyQueryDto;
import cn.kmbeast.pojo.entity.ScenicStrategy;
import cn.kmbeast.pojo.vo.ScenicStrategyListVO;
import cn.kmbeast.pojo.vo.ScenicStrategyVO;
import cn.kmbeast.service.ScenicStrategyService;
import cn.kmbeast.utils.TextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScenicStrategyServiceImpl implements ScenicStrategyService {

    @Autowired
    private ScenicStrategyMapper scenicStrategyMapper;

    @Override
    public Result<Void> save(ScenicStrategy scenicStrategy) {
        // 设置初始时间
        scenicStrategy.setCreateTime(LocalDateTime.now());
        // 设置上发布者的用户ID
        if(scenicStrategy.getUserId() == null){
            scenicStrategy.setUserId(LocalThreadHolder.getUserId());
        }
        // 默认未审核
        scenicStrategy.setIsAudit(false);

        int row = scenicStrategyMapper.save(scenicStrategy);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 批量删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicStrategyMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改攻略
    @Override
    public Result<Void> update(ScenicStrategy scenicStrategy) {
        // 修改后的攻略需重新审核
        scenicStrategy.setIsAudit(false);
        int row = scenicStrategyMapper.update(scenicStrategy);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询全部攻略
    @Override
    public Result<List<ScenicStrategyVO>> query(ScenicStrategyQueryDto scenicStrategyQueryDto) {
        List<ScenicStrategyVO> result = scenicStrategyMapper.query(scenicStrategyQueryDto);
        int total = scenicStrategyMapper.queryTotal(scenicStrategyQueryDto);
        return ApiResult.success(result, total);
    }

    // 通过审核
    @Override
    public Result<Void> audit(Integer id) {
        ScenicStrategy scenicStrategy = ScenicStrategy.builder().id(id).isAudit(true).build();
        int row = scenicStrategyMapper.update(scenicStrategy);
        if(row > 0){
            return ApiResult.success("审核成功！");
        }
        return ApiResult.error("审核失败！");
    }

    @Override
    public Result<List<ScenicStrategyListVO>> queryList(ScenicStrategyQueryDto dto) {
        // 只能查已经审核通过的内容
        dto.setIsAudit(true);
        Integer totalCount = scenicStrategyMapper.queryTotal(dto);

        // 查询全部的攻略
        List<ScenicStrategyVO> scenicStrategyVOS = scenicStrategyMapper.query(dto);
        List<ScenicStrategyListVO> scenicStrategyListVOS = new ArrayList<>();
        for (ScenicStrategyVO scenicStrategyVO : scenicStrategyVOS) {
            ScenicStrategyListVO scenicStrategyListVO = new ScenicStrategyListVO();
            BeanUtils.copyProperties(scenicStrategyVO, scenicStrategyListVO);
            // 处理简要 --- 现在是富文本类型 --- 需要处理
            String detail = TextUtils.extractText(scenicStrategyVO.getContent(), 200);
            scenicStrategyListVO.setDetail(detail);
            scenicStrategyListVOS.add(scenicStrategyListVO);
        }

        return ApiResult.success(scenicStrategyListVOS, totalCount);
    }
}
