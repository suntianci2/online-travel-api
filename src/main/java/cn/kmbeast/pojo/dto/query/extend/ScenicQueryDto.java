package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicQueryDto extends QueryDto {
    // 景点名称
    private String name;

    // 所属类别
    private String categoryName;

    // 景点状态
    private Boolean status;

    // 供应商id
    private Integer vendorId;

    // 收藏者id列表
    private String saveIds;
}
