package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 酒店订单实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelOrderInfo {

    // 订单id
    private Integer id;

    // 房间id
    private Integer roomId;

    // 用户ID
    private Integer userId;

    // 联系人
    private String concatPerson;

    // 联系电话
    private String concatPhone;

    // 金额
    private BigDecimal amount;

    // 支付状态
    private Boolean payStatus;

    // 支付时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
