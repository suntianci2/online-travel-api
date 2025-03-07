package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.ScenicTicket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketVO extends ScenicTicket {
    // 景点名称
    private String scenicName;
}
