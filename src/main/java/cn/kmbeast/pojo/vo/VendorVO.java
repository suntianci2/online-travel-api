package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.Vendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 别忘了加这两个注解，不然外键在前端无法显示
@Data
@EqualsAndHashCode(callSuper = true)
public class VendorVO extends Vendor {
    // 外键，负责人姓名
    private String userName;
}
