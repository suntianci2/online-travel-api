package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.ScenicRating;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicRatingVO extends ScenicRating {
    // 景区名称
    public String scenicName;

    // 评分用户名称
    public String userName;

    // 用户头像
    private String userAvatar;
}
