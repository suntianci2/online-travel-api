package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 供应商实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

    // 主键id
    private Integer id;

    // 名称
    private String name;

    // 联系人
    private String concatPerson;

    // 联系电话
    private String concatPhone;

    // 邮箱
    private String email;

    // 产品类型
    private String productType;

    // 工作地址
    private String workAddress;

    // 用户id
    private Integer userId;

    // 审核状态
    private Boolean isAudit;

    // 状态
    private Boolean status;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
