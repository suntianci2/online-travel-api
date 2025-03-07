package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketOrderQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.ScenicTicketOrder;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.ScenicTicketOrderVO;

import java.util.List;

public interface ScenicTicketOrderService {
    // 新增
    public Result<Void> save(ScenicTicketOrder scenicTicketOrder);

    // 批量删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(ScenicTicketOrder scenicTicketOrder);

    // 分页查询
    Result<List<ScenicTicketOrderVO>> query(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto);

    // 查询指定服务商的景点订单
    Result<List<ScenicTicketOrderVO>> queryScenicTicketOrder(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto);

    // 往前统计指定时间内成交的金额
    Result<List<ChartVO>> daysQuery(Integer day);

    // 支付
    Result<Void> pay(ScenicTicketOrder scenicTicketOrder);

    // 查询用户数据
    Result<List<ChartVO>> daysQueryUser(Integer day);

    // 统计全站指定日期内的成交门票额
    Result<List<ChartVO>> daysQueryMoney(Integer day);
}
