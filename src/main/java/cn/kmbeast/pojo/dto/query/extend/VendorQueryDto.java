package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class VendorQueryDto extends QueryDto {
    // 供应商名称
    private String name;

    // 审核状态
    private Boolean isAudit;

    // 可用状态
    private Boolean status;

    // 用户id
    private Integer userId;

}
