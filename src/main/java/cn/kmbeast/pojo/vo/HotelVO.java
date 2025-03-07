package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.Hotel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotelVO extends Hotel {
    // 供应商名名称
    private String vendorName;
}
