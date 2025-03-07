package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.service.HotelOrderInfoService;
import cn.kmbeast.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelOrderInfo")
public class HotelOrderInfoController {
    @Autowired
    private HotelOrderInfoService hotelOrderInfoService;

    // 增加
    @PostMapping("/save")
    public Result<Void> save(@RequestBody HotelOrderInfo hotelOrderInfo){
        return hotelOrderInfoService.save(hotelOrderInfo);
    }

    // 批量删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return  hotelOrderInfoService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody HotelOrderInfo hotelOrderInfo){
        return hotelOrderInfoService.update(hotelOrderInfo);
    }

    // 分页查询信息
    @Pager
    @PostMapping("/query")
    public Result<List<HotelOrderInfoVO>> query(@RequestBody HotelOrderInfoQueryDto hotelOrderInfoQueryDto){
        return hotelOrderInfoService.query(hotelOrderInfoQueryDto);
    }

    // 查询指定供应商的酒店订单信息
    @Pager
    @PostMapping("/queryHotelOrderVendor")
    public Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(@RequestBody HotelOrderInfoQueryDto hotelOrderInfoQueryDto){
        return hotelOrderInfoService.queryHotelOrderVendor(hotelOrderInfoQueryDto);
    }

    // 酒店订单支付
    @Pager
    @PostMapping(value = "/pay")
    @ResponseBody
    public Result<Void> pay(@RequestBody HotelOrderInfo hotelOrderInfo) {
        return hotelOrderInfoService.pay(hotelOrderInfo);
    }

    // 查询用户的酒店订单
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<HotelOrderInfoVO>> queryUser(@RequestBody HotelOrderInfoQueryDto dto) {
        // 设置上用户ID
        dto.setUserId(LocalThreadHolder.getUserId());
        return hotelOrderInfoService.query(dto);
    }

    // 绘制销售额折线图
    @GetMapping("/daysQuery/{day}")
    public Result<List<ChartVO>> daysQuery(@PathVariable Integer day) {
        return hotelOrderInfoService.daysQuery(day);
    }


    // 统计全站指定日期里面的成交门票金额
    @GetMapping(value = "/daysQueryMoney/{day}")
    @ResponseBody
    public Result<List<ChartVO>> daysQueryMoney(@PathVariable Integer day) {
        return hotelOrderInfoService.daysQueryMoney(day);
    }

}
