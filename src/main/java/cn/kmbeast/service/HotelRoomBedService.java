package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomBedQueryDto;
import cn.kmbeast.pojo.entity.HotelRoomBed;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HotelRoomBedService {
    // 增加
    public Result<Void> save(HotelRoomBed hotelRoomBed);

    // 删除
    public Result<Void> batchDelete(List<Integer> ids);

    // 修改
    public Result<Void> update(HotelRoomBed hotelRoomBed);

    // 查询
    public Result<List<HotelRoomBed>> query(HotelRoomBedQueryDto hotelRoomBedQueryDto);
}
