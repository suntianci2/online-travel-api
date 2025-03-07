package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.pojo.vo.ScenicVO;
import cn.kmbeast.service.HotelRoomService;
import cn.kmbeast.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelRoom")
public class HotelRoomController {
    @Autowired
    private HotelRoomService hotelRoomService;

    // 增加
    @PostMapping("/save")
    public Result<Void> save(@RequestBody HotelRoom hotelRoom){
        return hotelRoomService.save(hotelRoom);
    }

    // 批量删除
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return  hotelRoomService.batchDelete(ids);
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody HotelRoom hotelRoom){
        return hotelRoomService.update(hotelRoom);
    }

    // 分页查询信息
    @Pager
    @PostMapping("/query")
    public Result<List<HotelRoomVO>> query(@RequestBody HotelRoomQueryDto hotelRoomQueryDto){
        return hotelRoomService.query(hotelRoomQueryDto);
    }

    // 查询指定服务商下的所有酒店房间
    @Pager
    @PostMapping("/queryHotelRoom")
    public Result<List<HotelRoomVO>> queryHotelRoom(@RequestBody HotelRoomQueryDto hotelRoomQueryDto){
        return hotelRoomService.queryHotelRoom(hotelRoomQueryDto);
    }

}
