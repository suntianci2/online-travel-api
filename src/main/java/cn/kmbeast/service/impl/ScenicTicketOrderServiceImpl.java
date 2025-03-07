package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.*;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.dto.query.extend.*;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.ScenicTicket;
import cn.kmbeast.pojo.entity.ScenicTicketOrder;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.*;
import cn.kmbeast.service.HotelOrderInfoService;
import cn.kmbeast.service.ScenicTicketOrderService;
import cn.kmbeast.utils.DateUtil;
import cn.kmbeast.utils.DecimalUtils;
import cn.kmbeast.utils.MoneyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScenicTicketOrderServiceImpl implements ScenicTicketOrderService {

    @Autowired
    private ScenicTicketOrderMapper scenicTicketOrderMapper;

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private ScenicMapper scenicMapper;

    @Autowired
    private ScenicTicketMapper scenicTicketMapper;

    // 增加
    @Override
    public Result<Void> save(ScenicTicketOrder scenicTicketOrder) {


        // 判断是否存在未支付订单（针对下单人）
        ScenicTicketOrder findNotPay = ScenicTicketOrder.builder().userId(scenicTicketOrder.getUserId()).payStatus(false).build();
        ScenicTicketOrder existNoPay = scenicTicketOrderMapper.getByActive(findNotPay);
        if(existNoPay != null){
            return ApiResult.error("存在未支付订单，不可继续下单！");
        }

        if (!StringUtils.hasText(scenicTicketOrder.getConcatPerson()) ||
                !StringUtils.hasText(scenicTicketOrder.getConcatPhone())) {
            return ApiResult.error("联系人或联系电话为空");
        }
        ScenicTicketQueryDto scenicTicketQueryDto = new ScenicTicketQueryDto();
        scenicTicketQueryDto.setId(scenicTicketOrder.getTicketId());
        List<ScenicTicketVO> scenicTicketVOS = scenicTicketMapper.query(scenicTicketQueryDto);
        if (scenicTicketVOS.isEmpty()) {
            return ApiResult.error("暂无门票信息");
        }
        ScenicTicketVO scenicTicketVO = scenicTicketVOS.get(0);
        // 门票是否可用
        if (!scenicTicketVO.getUseStatus()) {
            return ApiResult.error("门票暂不可用");
        }
        // 看门票是否充足
        if (scenicTicketOrder.getBuyNumber() > scenicTicketVO.getNumber()) {
            return ApiResult.error("门票库存不足");
        }
        BigDecimal amount = DecimalUtils.calculateTotal(
                scenicTicketOrder.getBuyNumber(),
                scenicTicketVO.getPrice(),
                scenicTicketVO.getDiscount() == null ? 1 : (scenicTicketVO.getDiscount() / 10)
        );

        // 设置金额
        scenicTicketOrder.setAmount(amount);
        // 将购买者的用户ID设置上
        scenicTicketOrder.setUserId(LocalThreadHolder.getUserId());
        // 设置初始时间
        scenicTicketOrder.setCreateTime(LocalDateTime.now());
        scenicTicketOrder.setTicketId(scenicTicketVO.getId());
        // 开始的时候，订单的状态是未支付的
        scenicTicketOrder.setPayStatus(false);
        int row = scenicTicketOrderMapper.save(scenicTicketOrder);
        // 改门票数量
        ScenicTicket scenicTicket = new ScenicTicket();
        scenicTicket.setId(scenicTicketVO.getId());
        scenicTicket.setNumber(scenicTicketVO.getNumber() - scenicTicketOrder.getBuyNumber());
        scenicTicketMapper.update(scenicTicket);
        if(row > 0){
            return ApiResult.success("购买成功！");
        }
        return ApiResult.error("购买失败！");

    }

    // 批量删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicTicketOrderMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改
    @Override
    public Result<Void> update(ScenicTicketOrder scenicTicketOrder) {
        int row = scenicTicketOrderMapper.update(scenicTicketOrder);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询
    @Override
    public Result<List<ScenicTicketOrderVO>> query(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto) {
        // 查询分页结果
        List<ScenicTicketOrderVO> data = scenicTicketOrderMapper.query(scenicTicketOrderQueryDto);
        // 查询分页总数
        int total = scenicTicketOrderMapper.queryTotal(scenicTicketOrderQueryDto);
        return ApiResult.success(data, total);
    }

    // 查询指定服务商的景点订单
    @Override
    public Result<List<ScenicTicketOrderVO>> queryScenicTicketOrder(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto) {
        // 获取当前用户所对应的服务商
        Integer userId = LocalThreadHolder.getUserId();
        Vendor vendor4Query = Vendor.builder().userId(userId).build();
        Vendor vendorIsExist = vendorMapper.getByActive(vendor4Query);
        if(vendorIsExist == null){
            return ApiResult.error("当前用户不存在相应服务商！");
        }

        // 获取当前服务商所有的景点id
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setVendorId(vendorIsExist.getId());
        List<ScenicVO> allScenic = scenicMapper.query(scenicQueryDto);
        // 使用stream流获取其中的所有景点id
        List<Integer> scenicIds = allScenic.stream()
                .map(ScenicVO::getId)
                .collect(Collectors.toList());


        // 根据门票id查询全部订单
        scenicTicketOrderQueryDto.setScenicIds(scenicIds);
        List<ScenicTicketOrderVO> data = scenicTicketOrderMapper.query(scenicTicketOrderQueryDto);
        int total = scenicTicketOrderMapper.queryTotal(scenicTicketOrderQueryDto);
        return ApiResult.success(data, total);
    }

    // 往前统计指定时间内的销售额
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        // 将day转化为开始时间和结束时间（以现在时间为基准，结束时间为现在时间，开始时间根据现在时间和day进行计算）
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        ScenicTicketOrderQueryDto scenicTicketOrderQueryDto = new ScenicTicketOrderQueryDto();
        scenicTicketOrderQueryDto.setStartTime(queryDto.getStartTime());
        scenicTicketOrderQueryDto.setEndTime(queryDto.getEndTime());

        // 根据转化后的时间查询时间范围内的订单
        Result<List<ScenicTicketOrderVO>> listResult = queryScenicTicketOrder(scenicTicketOrderQueryDto);
        ApiResult<List<ScenicTicketOrderVO>> dataResult = (ApiResult)listResult;
        List<ScenicTicketOrderVO> data = dataResult.getData();

        // 获取金额和时间
        List<MoneyDto> moneyDtoList = data.stream()
                .map(scenicTicketOrderVO -> new MoneyDto(scenicTicketOrderVO.getAmount(), scenicTicketOrderVO.getPayTime()))
                .collect(Collectors.toList());

        // 根据金额和时间绘制图表
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);


    }

    // 订单支付
    @Override
    public Result<Void> pay(ScenicTicketOrder scenicTicketOrder) {
        // 支付状态
        scenicTicketOrder.setPayStatus(true);
        // 支付事件
        scenicTicketOrder.setPayTime(LocalDateTime.now());
        scenicTicketOrderMapper.update(scenicTicketOrder);
        return ApiResult.success();
    }

    /**
     * 查询用户自己的消费数据
     *
     * @param day 往前查多少天
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> daysQueryUser(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        ScenicTicketOrderQueryDto dto = new ScenicTicketOrderQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        // 设置上用户的ID
        dto.setUserId(LocalThreadHolder.getUserId());
        List<ScenicTicketOrderVO> scenicTicketOrderVOS = scenicTicketOrderMapper.query(dto);
        List<MoneyDto> moneyDtoList = scenicTicketOrderVOS.stream()
                .map(scenicTicketOrderVO -> new MoneyDto(
                        scenicTicketOrderVO.getAmount(),
                        scenicTicketOrderVO.getPayTime())).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

    // 统计全站指定日期里面的成交门票金额
    @Override
    public Result<List<ChartVO>> daysQueryMoney(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        ScenicTicketOrderQueryDto dto = new ScenicTicketOrderQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        List<ScenicTicketOrderVO> scenicTicketOrderVOS = scenicTicketOrderMapper.query(dto);
        List<MoneyDto> moneyDtoList = scenicTicketOrderVOS.stream()
                .map(scenicTicketOrderVO -> new MoneyDto(
                        scenicTicketOrderVO.getAmount(),
                        scenicTicketOrderVO.getPayTime())).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

}
