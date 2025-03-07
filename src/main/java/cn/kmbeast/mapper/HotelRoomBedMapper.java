package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.HotelRoomBedQueryDto;
import cn.kmbeast.pojo.entity.HotelRoomBed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelRoomBedMapper {

    // 新增
    int save(HotelRoomBed hotelRoomBed);

    // 删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(HotelRoomBed hotelRoomBed);

    // 分页查询
    List<HotelRoomBed> query(HotelRoomBedQueryDto hotelRoomBedQueryDto);

    // 分页查询条数
    int queryTotal(HotelRoomBedQueryDto hotelRoomBedQueryDto);
}
