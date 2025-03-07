package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.HotelOrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrderInfoVO extends HotelOrderInfo {
    // 房间号
    private String roomName;

}
