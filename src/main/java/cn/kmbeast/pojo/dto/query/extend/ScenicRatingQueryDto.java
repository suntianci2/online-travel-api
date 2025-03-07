package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicRatingQueryDto extends QueryDto {

    // 景点id
    public Integer scenicId;

    // 用户id
    public Integer userId;
}
