package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 酒店实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    // 酒店id
    private Integer id;

    // 酒店名称
    private String name;

    // 酒店封面
    private String cover;

    // 酒店地址
    private String address;

    // 酒店联系电话
    private String concatPhone;

    // 供应商、服务商id
    private Integer vendorId;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
