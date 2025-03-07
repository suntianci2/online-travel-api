package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.ScenicRatingMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicRatingQueryDto;
import cn.kmbeast.pojo.entity.ScenicRating;
import cn.kmbeast.pojo.vo.ScenicRatingVO;
import cn.kmbeast.service.ScenicRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScenicRatingServiceImpl implements ScenicRatingService {
    @Autowired
    private ScenicRatingMapper scenicRatingMapper;
    // 添加评分
    @Override
    public Result<Void> save(ScenicRating scenicRating) {
        ScenicRatingQueryDto scenicRatingQueryDto = new ScenicRatingQueryDto();
        scenicRatingQueryDto.setScenicId(scenicRating.getScenicId());
        scenicRatingQueryDto.setUserId(LocalThreadHolder.getUserId());
        Integer queryCount = scenicRatingMapper.queryTotal(scenicRatingQueryDto);
        if (queryCount != 0) {
            return ApiResult.error("已经评分过了");
        }
        // 设置评分时间
        scenicRating.setUserId(LocalThreadHolder.getUserId());
        scenicRating.setCreateTime(LocalDateTime.now());

        int row = scenicRatingMapper.save(scenicRating);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 删除评分
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicRatingMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 查询评分
    @Override
    public Result<List<ScenicRatingVO>> query(ScenicRatingQueryDto scenicRatingQueryDto) {
        List<ScenicRatingVO> result = scenicRatingMapper.query(scenicRatingQueryDto);
        int total = scenicRatingMapper.queryTotal(scenicRatingQueryDto);
        return ApiResult.success(result, total);
    }
}
