package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.ScenicTicketOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketOrderVO extends ScenicTicketOrder {
    // 景点id
    private Integer scenicId;

    // 景点名称
    private String scenicName;

    // 用户名
    private String userName;

}
