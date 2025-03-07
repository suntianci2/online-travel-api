package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrderInfoQueryDto extends QueryDto {
    // 支付状态
    private Boolean payStatus;

    // 用户ID
    private Integer userId;

    // 房间id列表
    private List<Integer> roomIds;

    // 单个房间id
    private Integer roomId;
}
