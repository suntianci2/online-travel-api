package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.ScenicLine;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicLineVO extends ScenicLine {
    // 景点名称
    public String scenicName;

    // 景点封面
    public String scenicCover;

    // 景点所在地址
    public String scenicAddress;

}
