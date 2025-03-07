package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicStrategyQueryDto;
import cn.kmbeast.pojo.entity.ScenicStrategy;
import cn.kmbeast.pojo.vo.ScenicStrategyListVO;
import cn.kmbeast.pojo.vo.ScenicStrategyVO;

import java.util.List;

public interface ScenicStrategyService {
    // 新增攻略
    Result<Void> save(ScenicStrategy scenicStrategy);

    // 批量删除攻略
    Result<Void> batchDelete(List<Integer> ids);

    // 修改攻略
    Result<Void> update(ScenicStrategy scenicStrategy);

    // 分页查询全部攻略
    public Result<List<ScenicStrategyVO>> query(ScenicStrategyQueryDto scenicStrategyQueryDto);

    // 通过审核
    Result<Void> audit(Integer id);

    // 查询攻略列表
    Result<List<ScenicStrategyListVO>> queryList(ScenicStrategyQueryDto dto);
}
