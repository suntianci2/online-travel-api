package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.entity.Hotel;
import cn.kmbeast.pojo.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelMapper {

    // 新增
    int save(Hotel hotel);

    // 批量删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(Hotel hotel);

    // 分页查询
    List<HotelVO> query(HotelQueryDto hotelQueryDto);

    // 分页查询总条数
    int queryTotal(HotelQueryDto hotelQueryDto);


}
