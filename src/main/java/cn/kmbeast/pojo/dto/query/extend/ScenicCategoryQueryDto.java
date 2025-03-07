package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 景点分类查询Dto类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicCategoryQueryDto extends QueryDto {
    // 景点分类名
    private String name;
}
