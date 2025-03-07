package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicRatingQueryDto;
import cn.kmbeast.pojo.entity.ScenicRating;
import cn.kmbeast.pojo.vo.ScenicRatingVO;

import java.util.List;

public interface ScenicRatingService {

    // 增加评分
    public Result<Void> save(ScenicRating scenicRating);

    // 删除评分
    public Result<Void> batchDelete(List<Integer> ids);

    // 分页查询评分
    public Result<List<ScenicRatingVO>> query(ScenicRatingQueryDto scenicRatingQueryDto);
}
