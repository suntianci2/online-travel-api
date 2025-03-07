package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicLineQueryDto extends QueryDto {
    // 对应景点id
    private int scenicId;

    // 路线细节
    private String detail;

}
