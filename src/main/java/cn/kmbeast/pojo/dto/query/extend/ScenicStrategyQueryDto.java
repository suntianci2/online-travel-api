package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicStrategyQueryDto extends QueryDto {
    // 审核状态
    private Boolean isAudit;

    // 攻略标题
    private String title;

    // 用户id
    private Integer userId;

    // 景点id
    private Integer scenicId;
}
