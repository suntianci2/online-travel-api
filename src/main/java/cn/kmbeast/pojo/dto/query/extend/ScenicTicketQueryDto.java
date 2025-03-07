package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketQueryDto extends QueryDto {
    // 可用状态
    private Boolean useStatus;

    // 介绍
    private String detail;

    // 景点id
    private Integer scenicId;

    // 服务商id
    private Integer VendorId;
}
