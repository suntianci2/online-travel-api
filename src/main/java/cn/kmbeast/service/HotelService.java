package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.entity.Hotel;
import cn.kmbeast.pojo.vo.HotelVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HotelService {

    // 新增
    Result<Void> save(Hotel hotel);

    // 批量删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改酒店信息
    Result<Void> update(Hotel hotel);

    // 分页查询
    Result<List<HotelVO>> query(HotelQueryDto hotelQueryDto);

    // 查询指定供应商id的酒店信息
    Result<List<HotelVO>> queryHotelVendor(HotelQueryDto hotelQueryDto);


}
