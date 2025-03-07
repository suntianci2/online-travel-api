package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicCategoryMapper {
    /**
     * 增加分类
     * @param scenicCategory    待增加的信息
     * @return 受影响行数
     */
    public int save(ScenicCategory scenicCategory);

    /**
     * 删除分类
     * @param ids   待删除的分类信息的id列表
     * @return  受影响行数
     */
    public int batchDelete(@Param("ids") List<Integer> ids);

    /**
     * 修改分类
     * @param scenicCategory
     * @return  受影响行数
     */
    public int update(ScenicCategory scenicCategory);

    /**
     * 查询分类
     * @param scenicCategoryQueryDto    封装了查询相关信息的类
     * @return  查询结果
     */
    public List<ScenicCategory> query(ScenicCategoryQueryDto scenicCategoryQueryDto);

    /**
     * 查询满足条件的总条数，用于分页查询
     * @param scenicCategoryQueryDto    封装了查询相关信息的类
     * @return  查询结果
     */
    int queryTotal(ScenicCategoryQueryDto scenicCategoryQueryDto);

    /**
     * 查询新的分类名是否已经存在
     * @param tempSc    封装了新的分类信息的类
     * @return  查询结果的封装类
     */
    ScenicCategory queryExist(ScenicCategory tempSc);
}
