package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketOrderQueryDto extends QueryDto {
    // 用户id
    private Integer userId;

    // 支付状态
    private Boolean payStatus;

    // 景点ids
    private List<Integer> scenicIds;

}
