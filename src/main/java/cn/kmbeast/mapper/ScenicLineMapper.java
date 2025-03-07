package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicLineQueryDto;
import cn.kmbeast.pojo.entity.ScenicLine;
import cn.kmbeast.pojo.vo.ScenicLineVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicLineMapper {

    // 添加路线
    int save(ScenicLine scenicLine);

    // 删除路线
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改路线
    int update(ScenicLine scenicLine);

    // 查询路线
    public List<ScenicLineVO> query(ScenicLineQueryDto scenicLineQueryDto);

    // 查询路线总条数
    public int queryTotal(ScenicLineQueryDto scenicLineQueryDto);

    // 根据已有路线信息查询
    ScenicLine getByActive(ScenicLine tempScenicLine);


}
