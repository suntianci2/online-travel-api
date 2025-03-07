package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;

import java.util.List;

public interface ScenicCategoryService {
    // 新增
    Result<Void> save(ScenicCategory scenicCategory);

    // 删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(ScenicCategory scenicCategory);

    // 查询
    Result<List<ScenicCategory>> query(ScenicCategoryQueryDto scenicCategoryQueryDto);
}
