package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点分类实体
 */
// Lombok，自动生成对应的get和set方法
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicCategory {

    // 主键id
    private Integer id;

    // 分类名
    private String name;
}
