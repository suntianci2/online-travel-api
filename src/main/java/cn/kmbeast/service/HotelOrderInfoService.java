package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.HotelRoom;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.HotelRoomVO;

import java.util.List;

public interface HotelOrderInfoService {
    // 新增
    public Result<Void> save(HotelOrderInfo hotelOrderInfo);

    // 批量删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(HotelOrderInfo hotelOrderInfo);

    // 分页查询
    Result<List<HotelOrderInfoVO>> query(HotelOrderInfoQueryDto hotelOrderInfoQueryDto);

    // 查询指定供应商的酒店订单信息
    Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(HotelOrderInfoQueryDto hotelOrderInfoQueryDto);

    // 绘制销售额折线图
    Result<List<ChartVO>> daysQuery(Integer day);

    // 酒店订单支付
    Result<Void> pay(HotelOrderInfo hotelOrderInfo);

    // 统计全站指定日期里面的成交门票金额
    Result<List<ChartVO>> daysQueryMoney(Integer day);
}
