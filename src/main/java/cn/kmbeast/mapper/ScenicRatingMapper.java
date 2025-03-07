package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicRatingQueryDto;
import cn.kmbeast.pojo.entity.ScenicRating;
import cn.kmbeast.pojo.vo.ScenicRatingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScenicRatingMapper {
    // 新增评分
    int save(ScenicRating scenicRating);

    // 删除评分
    int batchDelete(List<Integer> ids);

    // 分页查询评分
    List<ScenicRatingVO> query(ScenicRatingQueryDto scenicRatingQueryDto);

    // 查询结果总数
    int queryTotal(ScenicRatingQueryDto scenicRatingQueryDto);
}
