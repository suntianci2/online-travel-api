package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Notice;
import cn.kmbeast.service.NoticeService;
import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    // 新增
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Notice notice){
        return noticeService.save(notice);
    }

    // 删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return noticeService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Notice notice){
        return noticeService.update(notice);
    }

    // 分页查询
    @Pager
    @PostMapping("/query")
    public Result<List<Notice>> query(@RequestBody NoticeQueryDto noticeQueryDto){
        return noticeService.query(noticeQueryDto);
    }
}
