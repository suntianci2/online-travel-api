package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 景点门票实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicTicket {

    // 主键id
    private Integer id;

    // 景点id
    private Integer scenicId;

    // 描述细节
    private String detail;

    // 数量
    private Integer number;

    // 价格
    private BigDecimal price;

    // 折扣
    private Double discount;

    // 使用状态
    private Boolean useStatus;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
