package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HotelMapper;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.entity.Hotel;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.HotelVO;
import cn.kmbeast.service.HotelService;
import cn.kmbeast.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private VendorMapper vendorMapper;

    // 新增
    @Override
    public Result<Void> save(Hotel hotel) {
        // 酒店名称允许重复，所以这里不进行唯一性判断
        // 设置服务商id为当前操作的用户
        if(hotel.getVendorId() == null){
            // 获取当前用户id所对应的供应商id
            Vendor queryVendor = Vendor.builder().userId(LocalThreadHolder.getUserId()).build();
            Vendor vendorIsExist = vendorMapper.getByActive(queryVendor);
            // 不存在相应供应商，则不允许添加。
            if(vendorIsExist == null){
                return ApiResult.error("当前用户不存在相应供应商！");
            }
            hotel.setVendorId(vendorIsExist.getId());
        }
        hotel.setCreateTime(LocalDateTime.now());
        int row = hotelMapper.save(hotel);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 批量删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = hotelMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改酒店信息
    @Override
    public Result<Void> update(Hotel hotel) {
        int row = hotelMapper.update(hotel);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询
    @Override
    public Result<List<HotelVO>> query(HotelQueryDto hotelQueryDto) {
        List<HotelVO> result = hotelMapper.query(hotelQueryDto);
        int total = hotelMapper.queryTotal(hotelQueryDto);
        return ApiResult.success(result, total);
    }

    // 查询指定供应商id的酒店信息
    @Override
    public Result<List<HotelVO>> queryHotelVendor(HotelQueryDto hotelQueryDto) {
        // 获取当前用户id所对应的供应商id
        Vendor queryVendor = Vendor.builder().userId(LocalThreadHolder.getUserId()).build();
        Vendor vendorIsExist = vendorMapper.getByActive(queryVendor);
        if(vendorIsExist == null){
            return ApiResult.error("当前用户不存在相应供应商！");
        }
        hotelQueryDto.setVendorId(vendorIsExist.getId());
        List<HotelVO> result = hotelMapper.query(hotelQueryDto);
        int total = hotelMapper.queryTotal(hotelQueryDto);
        return ApiResult.success(result, total);
    }
}
