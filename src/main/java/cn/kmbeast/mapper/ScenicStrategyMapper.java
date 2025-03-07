package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicStrategyQueryDto;
import cn.kmbeast.pojo.entity.ScenicStrategy;
import cn.kmbeast.pojo.vo.ScenicStrategyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicStrategyMapper {

    // 新增攻略
    int save(ScenicStrategy scenicStrategy);

    // 批量删除攻略
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改攻略
    int update(ScenicStrategy scenicStrategy);

    // 分页查询攻略
    List<ScenicStrategyVO> query(ScenicStrategyQueryDto scenicStrategyQueryDto);

    // 分页查询总条数
    int queryTotal(ScenicStrategyQueryDto scenicStrategyQueryDto);

}
