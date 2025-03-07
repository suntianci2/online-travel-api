package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.ScenicVO;
import cn.kmbeast.pojo.vo.SelectedVO;
import cn.kmbeast.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenic")
public class ScenicController {
    @Autowired
    private ScenicService scenicService;

    // 增加景点
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Scenic scenic){
        return scenicService.save(scenic);
    }

    // 批量删除景点
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return  scenicService.batchDelete(ids);
    }

    // 修改景点
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Scenic scenic){
        return scenicService.update(scenic);
    }

    // 分页查询景点信息
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicVO>> query(@RequestBody ScenicQueryDto scenicQueryDto){
        return scenicService.query(scenicQueryDto);
    }

    // 查询指定供应商id的景点信息
    @Pager
    @PostMapping("/queryVendorScenic")
    public Result<List<ScenicVO>> queryVendorScenic(@RequestBody ScenicQueryDto scenicQueryDto){
        return scenicService.queryVendorScenic(scenicQueryDto);
    }

    // 关联景点下拉选择器
    @GetMapping(value = "/querySelectedScenic")
    @ResponseBody
    public Result<List<SelectedVO>> querySelectedScenic() {
        return scenicService.querySelectedScenic();
    }


    // 浏览操作

    @Pager
    @PostMapping(value = "/viewOperation/{scenicId}")
    @ResponseBody
    public Result<Void> viewOperation(@PathVariable Integer scenicId) {
        return scenicService.viewOperation(scenicId);
    }

    // 收藏操作

    @Pager
    @PostMapping(value = "/saveOperation/{scenicId}")
    @ResponseBody
    public Result<Void> saveOperation(@PathVariable Integer scenicId) {
        return scenicService.saveOperation(scenicId);
    }


    // 查询用户的收藏景点状况
    @Pager
    @PostMapping(value = "/saveStatus/{scenicId}")
    @ResponseBody
    public Result<Boolean> saveStatus(@PathVariable Integer scenicId) {
        return scenicService.saveStatus(scenicId);
    }

    // 查询用户收藏的景点信息
    @Pager
    @GetMapping(value = "/querySave")
    @ResponseBody
    public Result<List<ScenicVO>> querySave() {
        return scenicService.querySave();
    }

}
