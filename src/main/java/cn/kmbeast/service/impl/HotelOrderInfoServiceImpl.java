package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HotelMapper;
import cn.kmbeast.mapper.HotelOrderInfoMapper;
import cn.kmbeast.mapper.HotelRoomMapper;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelQueryDto;
import cn.kmbeast.pojo.dto.query.extend.HotelRoomQueryDto;
import cn.kmbeast.pojo.dto.query.extend.MoneyDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.HotelRoomVO;
import cn.kmbeast.pojo.vo.HotelVO;
import cn.kmbeast.service.HotelOrderInfoService;
import cn.kmbeast.utils.DateUtil;
import cn.kmbeast.utils.DecimalUtils;
import cn.kmbeast.utils.MoneyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HotelOrderInfoServiceImpl implements HotelOrderInfoService {

    @Autowired
    private HotelOrderInfoMapper hotelOrderInfoMapper;

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    // 增加
    @Override
    public Result<Void> save(HotelOrderInfo hotelOrderInfo) {
        // 判断输入数据是否为null
        if (!StringUtils.hasText(hotelOrderInfo.getConcatPerson()) ||
                !StringUtils.hasText(hotelOrderInfo.getConcatPhone())) {
            return ApiResult.error("联系人或联系电话为空");
        }

        // 判断是否存在未支付订单（针对下单人）
        HotelOrderInfo findNotPay = HotelOrderInfo.builder().userId(hotelOrderInfo.getUserId()).payStatus(false).build();
        HotelOrderInfo existNoPay = hotelOrderInfoMapper.getByActive(findNotPay);
        if (existNoPay != null) {
            return ApiResult.error("存在未支付订单，不可继续下单！");
        }

        HotelRoomQueryDto hotelRoomQueryDto = new HotelRoomQueryDto();
        hotelRoomQueryDto.setId(hotelOrderInfo.getRoomId());
        List<HotelRoomVO> hotelRoomVOS = hotelRoomMapper.query(hotelRoomQueryDto);
        if (hotelRoomVOS.isEmpty()) {
            return ApiResult.error("房间信息未找到");
        }
        HotelRoomVO hotelRoomVO = hotelRoomVOS.get(0);
        Double discount = hotelRoomVO.getDiscount();
        // 没有设计买多少晚，这里使用1就行
        BigDecimal amount = DecimalUtils.calculateTotal(
                1,
                hotelRoomVO.getPrice(),
                discount == null ? 1 : (discount / 10)
        );
        hotelOrderInfo.setAmount(amount);
        // 设置用户ID
        hotelOrderInfo.setUserId(LocalThreadHolder.getUserId());
        // 开始的时候，订单的状态是未支付的
        hotelOrderInfo.setPayStatus(false);
        // 设置当前时间为创建时间
        hotelOrderInfo.setCreateTime(LocalDateTime.now());
        int row = hotelOrderInfoMapper.save(hotelOrderInfo);
        if (row > 0) {
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");

    }

    // 批量删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = hotelOrderInfoMapper.batchDelete(ids);
        if (row > 0) {
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改
    @Override
    public Result<Void> update(HotelOrderInfo hotelOrderInfo) {
        int row = hotelOrderInfoMapper.update(hotelOrderInfo);
        if (row > 0) {
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 分页查询
    @Override
    public Result<List<HotelOrderInfoVO>> query(HotelOrderInfoQueryDto hotelOrderInfoQueryDto) {
        // 查询分页结果
        List<HotelOrderInfoVO> result = hotelOrderInfoMapper.query(hotelOrderInfoQueryDto);
        // 查询分页总数
        int total = hotelOrderInfoMapper.queryTotal(hotelOrderInfoQueryDto);
        return ApiResult.success(result, total);
    }

    // 查询指定供应商的酒店信息
    @Override
    public Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(HotelOrderInfoQueryDto hotelOrderInfoQueryDto) {
        // 查询当前操作用户所对应的供应商
        Vendor vendor4Query = new Vendor();
        vendor4Query.setUserId(LocalThreadHolder.getUserId());
        Vendor vendorIsExist = vendorMapper.getByActive(vendor4Query);
        if (vendorIsExist == null || !vendorIsExist.getStatus() || !vendorIsExist.getIsAudit()) {
            return ApiResult.error("当前用户不存在相应服务商，或服务商未通过审核或异常！");
        }

        // 根据供应商id查询其名下的所有酒店信息，并使用stream流提取其所有酒店id
        HotelQueryDto hotelQueryDto = new HotelQueryDto();
        hotelQueryDto.setVendorId(vendorIsExist.getId());
        List<HotelVO> allHotels = hotelMapper.query(hotelQueryDto);
        if (allHotels.isEmpty()) {
            return ApiResult.error("名下无管理酒店");
        }
        List<Integer> hotelIds = allHotels.stream()
                .map(HotelVO::getId)
                .collect(Collectors.toList());

        // 根据酒店id列表查询所有酒店房间，并使用stream流提取其所有酒店id
        HotelRoomQueryDto hotelRoomQueryDto = new HotelRoomQueryDto();
        hotelRoomQueryDto.setHotelIds(hotelIds);
        List<HotelRoomVO> allRooms = hotelRoomMapper.query(hotelRoomQueryDto);
        List<Integer> roomIds = allRooms.stream()
                .map(HotelRoomVO::getId)
                .collect(Collectors.toList());

        // 根据房间id查询所有的订单
        hotelOrderInfoQueryDto.setRoomIds(roomIds);
        List<HotelOrderInfoVO> result = hotelOrderInfoMapper.query(hotelOrderInfoQueryDto);
        int total = hotelOrderInfoMapper.queryTotal(hotelOrderInfoQueryDto);
        return ApiResult.success(result, total);
    }

    // 绘制销售额折线图
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        // 将day转化为起始时间和终止时间
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        HotelOrderInfoQueryDto hotelOrderInfoQueryDto = new HotelOrderInfoQueryDto();
        hotelOrderInfoQueryDto.setStartTime(queryDto.getStartTime());
        hotelOrderInfoQueryDto.setEndTime(queryDto.getEndTime());

        // 查询指定供应商的全部订单信息
        Result<List<HotelOrderInfoVO>> listResult = queryHotelOrderVendor(hotelOrderInfoQueryDto);
        ApiResult<List<HotelOrderInfoVO>> resultData = (ApiResult)listResult;
        List<HotelOrderInfoVO> allHotelOrderInfo = resultData.getData();

        // 使用stream流获取全部的金额和付款时间
        List<MoneyDto> MoneyData = allHotelOrderInfo.stream()
                .map(hotelOrderInfoVO -> new MoneyDto(hotelOrderInfoVO.getAmount(), hotelOrderInfoVO.getPayTime()))
                .collect(Collectors.toList());

        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, MoneyData);
        return ApiResult.success(chartVOS);
    }

    @Override
    public Result<Void> pay(HotelOrderInfo hotelOrderInfo) {
        hotelOrderInfo.setPayStatus(true);
        hotelOrderInfo.setPayTime(LocalDateTime.now());
        hotelOrderInfoMapper.update(hotelOrderInfo);
        return ApiResult.success();
    }

    @Override
    public Result<List<ChartVO>> daysQueryMoney(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        HotelOrderInfoQueryDto dto = new HotelOrderInfoQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        List<HotelOrderInfoVO> orderInfoVOS = hotelOrderInfoMapper.query(dto);
        List<MoneyDto> moneyDtoList = orderInfoVOS.stream().map(hotelOrderInfoVO -> new MoneyDto(
                hotelOrderInfoVO.getAmount(),
                hotelOrderInfoVO.getPayTime()
        )).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

}
