package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.vo.ScenicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicMapper {


    // 新增景点
    public int save(Scenic scenic);

    // 批量删除景点
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改景点信息
    int update(Scenic scenic);


    // 分页查询
    List<ScenicVO> query(ScenicQueryDto scenicQueryDto);

    // 查询分页数据总数
    int queryTotal(ScenicQueryDto scenicQueryDto);

    // 根据已有信息查询景点
    public Scenic getByActive(Scenic scenic);

    // 更新收藏者id
    void updateSaveIds(Scenic scenic);
}

