package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicLineQueryDto;
import cn.kmbeast.pojo.entity.ScenicLine;
import cn.kmbeast.pojo.vo.ScenicLineVO;
import cn.kmbeast.service.ScenicLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenicLine")
public class ScenicLineController {

    @Autowired
    private ScenicLineService scenicLineService;
    // 增加景点路线

    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicLine scenicLine){
        return scenicLineService.save(scenicLine);
    }
    // 删除景点路线
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return scenicLineService.batchDelete(ids);
    }

    // 修改景点路线
    @PutMapping("/update")
    public Result<Void> update(@RequestBody ScenicLine scenicLine){
        return scenicLineService.update(scenicLine);
    }

    // 查询景点路线
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicLineVO>> query(@RequestBody ScenicLineQueryDto scenicLineQueryDto){
        return scenicLineService.query(scenicLineQueryDto);
    }
}
