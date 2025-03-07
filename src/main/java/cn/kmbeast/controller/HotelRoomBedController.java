package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomBedQueryDto;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.entity.HotelRoomBed;
import cn.kmbeast.service.HotelRoomBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Pipe;
import java.util.List;

@RestController
@RequestMapping("/hotelRoomBed")
public class HotelRoomBedController {

    @Autowired
    private HotelRoomBedService hotelRoomBedService;

    // 新增
    @PostMapping("/save")
    public Result<Void> save(@RequestBody HotelRoomBed hotelRoomBed){
        return hotelRoomBedService.save(hotelRoomBed);
    }

    // 删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return hotelRoomBedService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody HotelRoomBed hotelRoomBed){
        return hotelRoomBedService.update(hotelRoomBed);
    }

    // 查询
    @Pager
    @PostMapping("/query")
    public Result<List<HotelRoomBed>> query(@RequestBody HotelRoomBedQueryDto hotelRoomBedQueryDto){
        return hotelRoomBedService.query(hotelRoomBedQueryDto);
    }
}
