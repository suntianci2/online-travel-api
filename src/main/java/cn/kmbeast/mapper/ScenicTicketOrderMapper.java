package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketOrderQueryDto;
import cn.kmbeast.pojo.entity.HotelOrderInfo;
import cn.kmbeast.pojo.entity.ScenicTicketOrder;
import cn.kmbeast.pojo.vo.HotelOrderInfoVO;
import cn.kmbeast.pojo.vo.ScenicTicketOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicTicketOrderMapper {

    // 新增
    public int save(ScenicTicketOrder scenicTicketOrder);

    // 批量删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(ScenicTicketOrder scenicTicketOrder);


    // 分页查询
    List<ScenicTicketOrderVO> query(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto);

    // 查询分页数据总数
    int queryTotal(ScenicTicketOrderQueryDto scenicTicketOrderQueryDto);

    // 根据已有条件查询指定信息
    ScenicTicketOrder getByActive(ScenicTicketOrder scenicTicketOrder);
}

