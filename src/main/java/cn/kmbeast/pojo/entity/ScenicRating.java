package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点评分实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicRating {

    // 主键id
    private Integer id;

    // 景点id
    private Integer scenicId;

    // 用户id
    private Integer userId;

    // 分数
    private Integer score;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
