package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点攻略实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicStrategy {

    // 主键id
    private Integer id;

    // 用户id
    private Integer userId;

    // 景点id
    private Integer scenicId;

    // 封面
    private String cover;

    // 标题
    private String title;

    // 内容
    private String content;

    // 审核状态
    private Boolean isAudit;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
