package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomQueryDto extends QueryDto {

    // 所属酒店名称
    private String hotelName;

    // 酒店房间名
    private String name;

    // 酒店ids
    private List<Integer> hotelIds;

    // 单个酒店id
    private Integer hotelId;
}
