package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.ScenicStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicStrategyVO extends ScenicStrategy {
    // 发表者用户名
    private String userName;

    // 景点名称
    private String scenicName;

    // 用户头像
    private String userAvatar;
}
