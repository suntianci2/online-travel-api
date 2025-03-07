package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HotelMapper;
import cn.kmbeast.mapper.HotelRoomMapper;
import cn.kmbeast.mapper.ScenicMapper;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.Hotel;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.pojo.vo.HotelVO;
import cn.kmbeast.pojo.vo.ScenicVO;
import cn.kmbeast.service.HotelRoomService;
import cn.kmbeast.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired HotelMapper hotelMapper;

    // 增加
    @Override
    public Result<Void> save(HotelRoom hotelRoom) {
        // 设置当前时间为创建时间
        hotelRoom.setCreateTime(LocalDateTime.now());
        int row = hotelRoomMapper.save(hotelRoom);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");

    }

    // 批量删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = hotelRoomMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改
    @Override
    public Result<Void> update(HotelRoom hotelRoom) {
        int row = hotelRoomMapper.update(hotelRoom);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询
    @Override
    public Result<List<HotelRoomVO>> query(HotelRoomQueryDto hotelRoomQueryDto) {
        // 查询分页结果
        List<HotelRoomVO> result = hotelRoomMapper.query(hotelRoomQueryDto);
        // 查询分页总数
        int total = hotelRoomMapper.queryTotal(hotelRoomQueryDto);
        return ApiResult.success(result, total);
    }

    // 查询指定服务商下的所有酒店房间
    @Override
    public Result<List<HotelRoomVO>> queryHotelRoom(HotelRoomQueryDto hotelRoomQueryDto) {
        // 获取当前操作用户的用户id
        Integer userId = LocalThreadHolder.getUserId();
        // 根据当前用户的id找到相应的服务商
        Vendor vendor4Query = Vendor.builder().userId(userId).build();
        Vendor vendorIsExist = vendorMapper.getByActive(vendor4Query);
        if(vendorIsExist == null){
            return ApiResult.error("当前用户不存在相应服务商！");
        }
        // 查询当前服务商所有的酒店id
        HotelQueryDto hotelQueryDto = new HotelQueryDto();
        hotelQueryDto.setVendorId(vendorIsExist.getId());
        List<HotelVO> allHotel = hotelMapper.query(hotelQueryDto);
        // 使用stream流获取所有的酒店id
        List<Integer> hotelIds = allHotel.stream()
                .map(HotelVO::getId)
                .collect(Collectors.toList());

        // 查询所有酒店id的所有的酒店房间
        hotelRoomQueryDto.setHotelIds(hotelIds);
        List<HotelRoomVO> result = hotelRoomMapper.query(hotelRoomQueryDto);
        int total = hotelRoomMapper.queryTotal(hotelRoomQueryDto);
        return ApiResult.success(result, total);
    }

}
