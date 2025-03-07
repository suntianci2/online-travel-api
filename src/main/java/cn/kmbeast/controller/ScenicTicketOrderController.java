package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketOrderQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.ScenicTicketOrder;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.ScenicTicketOrderVO;
import cn.kmbeast.service.HotelOrderInfoService;
import cn.kmbeast.service.ScenicTicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenicTicketOrder")
public class ScenicTicketOrderController {
    @Autowired
    private ScenicTicketOrderService scenicTicketOrderService;

    // 增加
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ScenicTicketOrder scenicTicketOrder){
        return scenicTicketOrderService.save(scenicTicketOrder);
    }

    // 批量删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return  scenicTicketOrderService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody ScenicTicketOrder scenicTicketOrder){
        return scenicTicketOrderService.update(scenicTicketOrder);
    }

    // 分页查询信息
    @Pager
    @PostMapping("/query")
    public Result<List<ScenicTicketOrderVO>> query(@RequestBody ScenicTicketOrderQueryDto scenicTicketOrderQueryDto){
        return scenicTicketOrderService.query(scenicTicketOrderQueryDto);
    }

    // 分页查询指定服务商下的景点订单信息
    @Pager
    @PostMapping("/queryScenicTicketOrder")
    public Result<List<ScenicTicketOrderVO>> queryScenicTicketOrder(@RequestBody ScenicTicketOrderQueryDto scenicTicketOrderQueryDto){
        return scenicTicketOrderService.queryScenicTicketOrder(scenicTicketOrderQueryDto);
    }

    /**
     * 门票订单支付
     *
     * @return Result<List<ScenicTicketOrderVO>>
     */
    @PostMapping(value = "/pay")
    @ResponseBody
    public Result<Void> pay(@RequestBody ScenicTicketOrder scenicTicketOrder) {
        return scenicTicketOrderService.pay(scenicTicketOrder);
    }

    /**
     * 查询用户的景点门票数据
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<ScenicTicketOrderVO>> queryUser(@RequestBody ScenicTicketOrderQueryDto dto) {
        // 设置上用户ID
        dto.setUserId(LocalThreadHolder.getUserId());
        return scenicTicketOrderService.query(dto);
    }

    /**
     * 统计用户成交金额
     *
     * @return Result<List < ChartVO>> 响应结果
     */
    @GetMapping(value = "/daysQueryUser/{day}")
    @ResponseBody
    public Result<List<ChartVO>> daysQueryUser(@PathVariable Integer day) {
        return scenicTicketOrderService.daysQueryUser(day);
    }

    // 查询销售额
    @GetMapping("/daysQuery/{day}")
    public Result<List<ChartVO>> daysQuery(@PathVariable Integer day) {
        return scenicTicketOrderService.daysQuery(day);
    }

    // 统计全站指定日期里面的成交门票金额
    @GetMapping(value = "/daysQueryMoney/{day}")
    @ResponseBody
    public Result<List<ChartVO>> daysQueryMoney(@PathVariable Integer day) {
        return scenicTicketOrderService.daysQueryMoney(day);
    }

}
