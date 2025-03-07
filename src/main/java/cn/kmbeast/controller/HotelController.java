package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.entity.Hotel;
import cn.kmbeast.pojo.vo.HotelVO;
import cn.kmbeast.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // 新增
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Hotel hotel){
        return hotelService.save(hotel);

    }

    // 批量删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return hotelService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Hotel hotel){
        return  hotelService.update(hotel);
    }
    // 分页查询
    @Pager
    @PostMapping("/query")
    public Result<List<HotelVO>> query(@RequestBody HotelQueryDto hotelQueryDto){
        return hotelService.query(hotelQueryDto);
    }

    // 查询指定供应商id的酒店信息
    @Pager
    @PostMapping("/queryHotelVendor")
    public Result<List<HotelVO>> queryHotelVendor(@RequestBody HotelQueryDto hotelQueryDto){
        return hotelService.queryHotelVendor(hotelQueryDto);
    }

}
