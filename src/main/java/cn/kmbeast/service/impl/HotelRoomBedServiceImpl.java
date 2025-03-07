package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.HotelRoomBedMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomBedQueryDto;
import cn.kmbeast.pojo.entity.HotelRoomBed;
import cn.kmbeast.service.HotelRoomBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelRoomBedServiceImpl implements HotelRoomBedService {

    @Autowired
    private HotelRoomBedMapper hotelRoomBedMapper;
    // 增加
    @Override
    public Result<Void> save(HotelRoomBed hotelRoomBed) {
        // 如果状态为null，则设置状态为false
        if(hotelRoomBed.getStatus() == null){
            hotelRoomBed.setStatus(false);
        }

        // 设置创建时间为现在
        hotelRoomBed.setCreateTime(LocalDateTime.now());
        int row = hotelRoomBedMapper.save(hotelRoomBed);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = hotelRoomBedMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改
    @Override
    public Result<Void> update(HotelRoomBed hotelRoomBed) {
        int row = hotelRoomBedMapper.update(hotelRoomBed);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 查询
    @Override
    public Result<List<HotelRoomBed>> query(HotelRoomBedQueryDto hotelRoomBedQueryDto) {
        List<HotelRoomBed> result = hotelRoomBedMapper.query(hotelRoomBedQueryDto);
        int total = hotelRoomBedMapper.queryTotal(hotelRoomBedQueryDto);
        return ApiResult.success(result, total);
    }
}
