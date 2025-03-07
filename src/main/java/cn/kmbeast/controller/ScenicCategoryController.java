package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.service.ScenicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // @Controller + @ResponseBody
@RequestMapping("/scenicCategory")
public class ScenicCategoryController {

    @Autowired
    private ScenicCategoryService scenicCategoryService;

    /**
     *  新增分类
     * @param scenicCategory 封装了新增的分类数据的实体类
     * @return Result<Void>
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicCategory scenicCategory){
        return scenicCategoryService.save(scenicCategory);
    }

    /**
     *  批量删除分类
     * @param ids   待删除的分类的id列表
     * @return  Result<Void>
     */
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return scenicCategoryService.batchDelete(ids);
    }

    /**
     *  修改景点分类
     * @param scenicCategory    封装的修改后的信息
     * @return  Result<Void>
     */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody ScenicCategory scenicCategory){
        return scenicCategoryService.update(scenicCategory);
    }

    /**
     *  查询景点分类
     * @param scenicCategoryQueryDto 封装了查询信息的类
     * @return  满足条件的景点分类的列表
     */
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicCategory>> query(@RequestBody ScenicCategoryQueryDto scenicCategoryQueryDto){
        return scenicCategoryService.query(scenicCategoryQueryDto);
    }


}
