package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.aop.Protector;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicStrategyQueryDto;
import cn.kmbeast.pojo.entity.ScenicStrategy;
import cn.kmbeast.pojo.vo.ScenicStrategyListVO;
import cn.kmbeast.pojo.vo.ScenicStrategyVO;
import cn.kmbeast.service.ScenicStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenicStrategy")
public class ScenicStrategyController {
    @Autowired
    private ScenicStrategyService scenicStrategyService;

    // 新增攻略
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicStrategy scenicStrategy) {
        return scenicStrategyService.save(scenicStrategy);
    }

    // 批量删除攻略
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return scenicStrategyService.batchDelete(ids);
    }

    // 修改攻略
    @PutMapping("/update")
    public Result<Void> update(@RequestBody ScenicStrategy scenicStrategy) {
        return scenicStrategyService.update(scenicStrategy);
    }

    // 分页查询全部攻略
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicStrategyVO>> query(@RequestBody ScenicStrategyQueryDto scenicStrategyQueryDto) {
        return scenicStrategyService.query(scenicStrategyQueryDto);
    }

    // 攻略通过审核
    @Protector(role = "管理员")
    @PostMapping("/audit/{id}")
    public Result<Void> audit(@PathVariable Integer id) {
        return scenicStrategyService.audit(id);
    }

    // 查询用户发表的攻略
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<ScenicStrategyVO>> queryUser(@RequestBody ScenicStrategyQueryDto dto) {
        // 设置上用户ID，数据隔离
        dto.setUserId(LocalThreadHolder.getUserId());
        return scenicStrategyService.query(dto);
    }

    // 查询攻略列表
    @Pager
    @PostMapping(value = "/queryList")
    @ResponseBody
    public Result<List<ScenicStrategyListVO>> queryList(@RequestBody ScenicStrategyQueryDto dto) {
        return scenicStrategyService.queryList(dto);
    }
}
