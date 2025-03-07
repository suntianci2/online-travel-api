package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.pojo.entity.ScenicTicket;
import cn.kmbeast.pojo.vo.ScenicTicketVO;

import java.util.List;

public interface ScenicTicketService {
    // 新增
    Result<Void> save(ScenicTicket scenicTicket);

    // 删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(ScenicTicket scenicTicket);

    // 查询
    Result<List<ScenicTicketVO>> query(ScenicTicketQueryDto scenicTicketQueryDto);

    // 查询指定服务商的门票信息
    Result<List<ScenicTicketVO>> queryTicketByVendor(ScenicTicketQueryDto scenicTicketQueryDto);


}
