package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicRatingQueryDto;
import cn.kmbeast.pojo.entity.ScenicRating;
import cn.kmbeast.pojo.vo.ScenicRatingVO;
import cn.kmbeast.service.ScenicRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenicRating")
public class ScenicRatingController {

    @Autowired
    private ScenicRatingService scenicRatingService;

    // 新增评分
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicRating scenicRating){
        return scenicRatingService.save(scenicRating);
    }

    // 删除评分
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return scenicRatingService.batchDelete(ids);
    }

    // 分页查询评分
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicRatingVO>> query(@RequestBody ScenicRatingQueryDto scenicRatingQueryDto){
        return scenicRatingService.query(scenicRatingQueryDto);
    }

    // 查询指定用户对景点的评分
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<ScenicRatingVO>> queryUser(@RequestBody ScenicRatingQueryDto dto) {
        dto.setUserId(LocalThreadHolder.getUserId());
        return scenicRatingService.query(dto);
    }


}
