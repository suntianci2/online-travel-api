package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomBedQueryDto extends QueryDto {

    // 房间id
    private Integer roomId;

    // 状态
    private Boolean status;
}
