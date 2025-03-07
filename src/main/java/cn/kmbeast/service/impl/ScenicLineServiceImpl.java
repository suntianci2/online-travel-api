package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.ScenicLineMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicLineQueryDto;
import cn.kmbeast.pojo.entity.ScenicLine;
import cn.kmbeast.pojo.vo.ScenicLineVO;
import cn.kmbeast.service.ScenicLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScenicLineServiceImpl implements ScenicLineService {
    @Autowired
    private ScenicLineMapper scenicLineMapper;

    // 新增景点路线
    @Override
    public Result<Void> save(ScenicLine scenicLine) {
        // 景点路线需要判断当前景点是否存在重复优先级的路线
        ScenicLine tempScenicLine = ScenicLine.builder().level(scenicLine.getLevel()).scenicId(scenicLine.getScenicId()).build();
        ScenicLine isExist = scenicLineMapper.getByActive(tempScenicLine);
        if (isExist != null) {
            // 表示该景点存在相同优先级的景点路线
            return ApiResult.error("该优先级路线已存在！");
        }
        // 设置创建时间为当前时间
        scenicLine.setCreateTime(LocalDateTime.now());
        int row = scenicLineMapper.save(scenicLine);
        if (row > 0) {
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 删除景点路线
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = scenicLineMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改景点路线
    @Override
    public Result<Void> update(ScenicLine scenicLine) {
        int row = scenicLineMapper.update(scenicLine);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败");
    }

    // 查询景点路线信息
    @Override
    public Result<List<ScenicLineVO>> query(ScenicLineQueryDto scenicLineQueryDto) {
        // 查询路线
        List<ScenicLineVO> result = scenicLineMapper.query(scenicLineQueryDto);
        // 查询总条数
        int total = scenicLineMapper.queryTotal(scenicLineQueryDto);
        // 返回封装结果
        return ApiResult.success(result, total);
    }
}
