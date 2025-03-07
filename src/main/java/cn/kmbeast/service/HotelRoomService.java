package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.pojo.vo.ScenicVO;

import java.util.List;

public interface HotelRoomService {
    // 新增
    public Result<Void> save(HotelRoom hotelRoom);

    // 批量删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(HotelRoom hotelRoom);

    // 分页查询
    Result<List<HotelRoomVO>> query(HotelRoomQueryDto hotelRoomQueryDto);

    // 查询指定服务商下的所有酒店房间
    Result<List<HotelRoomVO>> queryHotelRoom(HotelRoomQueryDto hotelRoomQueryDto);
}
