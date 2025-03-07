package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.Scenic;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 别忘了加这两个注解，不然外键在前端无法显示
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicVO extends Scenic {
    // 外键，类别名称
    private String categoryName;

    // 外键，服务商名称
    private String vendorName;

    // 外键，景点平均评分
    private Double ratingScore;

}
