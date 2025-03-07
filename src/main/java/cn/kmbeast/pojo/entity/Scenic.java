package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scenic {

    // 主键id
    private Integer id;

    // 景点名称
    private String name;

    // 分类id
    private Integer categoryId;

    // 封面
    private String cover;

    // 供应商id
    private Integer vendorId;

    // 收藏者用户id列表
    private String saveIds;

    // 浏览者用户id列表
    private String viewIds;

    // 地址
    private String address;

    // 细节
    private String detail;

    // 状态
    private Boolean status;
    
    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
