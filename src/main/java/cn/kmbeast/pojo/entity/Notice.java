package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 公告实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    // 主键id
    private Integer id;

    // 标题
    private String title;

    // 内容
    private String content;

    // 创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
