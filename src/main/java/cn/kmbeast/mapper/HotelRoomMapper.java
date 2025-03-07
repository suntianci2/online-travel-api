package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.pojo.vo.ScenicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelRoomMapper {

    // 新增
    public int save(HotelRoom hotelRoom);

    // 批量删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(HotelRoom hotelRoom);


    // 分页查询
    List<HotelRoomVO> query(HotelRoomQueryDto hotelRoomQueryDto);

    // 查询分页数据总数
    int queryTotal(HotelRoomQueryDto hotelRoomQueryDto);

}

