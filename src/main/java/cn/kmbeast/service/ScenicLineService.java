package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicLineQueryDto;
import cn.kmbeast.pojo.entity.ScenicLine;
import cn.kmbeast.pojo.vo.ScenicLineVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScenicLineService {
    // 新增景点路线
    Result<Void> save(ScenicLine scenicLine);

    // 删除景点路线
    Result<Void> batchDelete(List<Integer> ids);

    // 修改景点路线
    Result<Void> update(ScenicLine scenicLine);

    // 查询景点路线
    Result<List<ScenicLineVO>> query(ScenicLineQueryDto scenicLineQueryDto);

}
