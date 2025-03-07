package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelOrderInfoMapper {

    // 新增
    public int save(HotelOrderInfo hotelOrderInfo);

    // 批量删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(HotelOrderInfo hotelOrderInfo);


    // 分页查询
    List<HotelOrderInfoVO> query(HotelOrderInfoQueryDto hotelOrderInfoQueryDto);

    // 查询分页数据总数
    int queryTotal(HotelOrderInfoQueryDto hotelOrderInfoQueryDto);

    // 根据已有条件查询指定信息
    HotelOrderInfo getByActive(HotelOrderInfo findRoomExist);
}

