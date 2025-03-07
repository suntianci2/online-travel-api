package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.ScenicCategoryMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.service.ScenicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicCategoryServiceImpl implements ScenicCategoryService {

    @Autowired
    private ScenicCategoryMapper scenicCategoryMapper;

    /**
     * 增加景点分类
     *
     * @param scenicCategory 新增的信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(ScenicCategory scenicCategory) {
        // Lombok 形式的创建对象
        ScenicCategory tempSc = ScenicCategory.builder().name(scenicCategory.getName()).build();
        ScenicCategory nameExist = scenicCategoryMapper.queryExist(tempSc);
        if(nameExist != null){
            return ApiResult.error("该分类已存在！");
        }
        int row = scenicCategoryMapper.save(scenicCategory);
        if (row > 0) {
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    /**
     * 删除景点分类
     *
     * @param ids 待删除的分类的id列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicCategoryMapper.batchDelete(ids);
        if (row > 0) {
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败");
    }

    /**
     * 更新分类信息
     *
     * @param scenicCategory 待更新的分类信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(ScenicCategory scenicCategory) {
        int row = scenicCategoryMapper.update(scenicCategory);
        if (row > 0) {
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    /**
     * 查询分类信息
     *
     * @param scenicCategoryQueryDto 封装查询信息的类
     * @return Result<Void>
     */
    @Override
    public Result<List<ScenicCategory>> query(ScenicCategoryQueryDto scenicCategoryQueryDto) {
        List<ScenicCategory> queryOut = scenicCategoryMapper.query(scenicCategoryQueryDto);
        int total = scenicCategoryMapper.queryTotal(scenicCategoryQueryDto);
        return ApiResult.success(queryOut, total);
    }
}
