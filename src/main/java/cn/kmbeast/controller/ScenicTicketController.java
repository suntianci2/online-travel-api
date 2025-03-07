package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.pojo.entity.ScenicTicket;
import cn.kmbeast.pojo.vo.ScenicTicketVO;
import cn.kmbeast.service.ScenicCategoryService;
import cn.kmbeast.service.ScenicTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // @Controller + @ResponseBody
@RequestMapping("/scenicTicket")
public class ScenicTicketController {

    @Autowired
    private ScenicTicketService scenicTicketService;

    /**
     *  新增
     * @param scenicTicket 封装了新增的门票数据的实体类
     * @return Result<Void>
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicTicket scenicTicket){
        return scenicTicketService.save(scenicTicket);
    }

    /**
     *  批量删除
     * @param ids   待删除的门票的id列表
     * @return  Result<Void>
     */
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return scenicTicketService.batchDelete(ids);
    }

    /**
     *  修改景点门票
     * @param scenicTicket    封装的修改后的信息
     * @return  Result<Void>
     */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody ScenicTicket scenicTicket){
        return scenicTicketService.update(scenicTicket);
    }

    /**
     *  查询景点门票
     * @param scenicTicketQueryDto 封装了查询信息的类
     * @return  满足条件的景点门票的列表
     */
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicTicketVO>> query(@RequestBody ScenicTicketQueryDto scenicTicketQueryDto){
        return scenicTicketService.query(scenicTicketQueryDto);
    }

    // 查询指定供应商id的门票信息
    @Pager
    @PostMapping("/queryScenicTicket")
    public Result<List<ScenicTicketVO>> queryTicketByVendor(@RequestBody ScenicTicketQueryDto scenicTicketQueryDto){
        return scenicTicketService.queryTicketByVendor(scenicTicketQueryDto);
    }
}
