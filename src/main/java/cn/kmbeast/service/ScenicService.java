package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ScenicQueryDto;
import cn.kmbeast.pojo.entity.Scenic;
import cn.kmbeast.pojo.vo.ScenicVO;
import cn.kmbeast.pojo.vo.SelectedVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScenicService {
    // 新增景点
    public Result<Void> save(Scenic scenic);

    // 批量删除景点
    Result<Void> batchDelete(List<Integer> ids);

    // 修改景点信息
    Result<Void> update(Scenic scenic);

    // 分页查询景点
    Result<List<ScenicVO>> query(ScenicQueryDto scenicQueryDto);

    // 根据服务商id查询当前服务商的景点信息
    public Result<List<ScenicVO>> queryVendorScenic(ScenicQueryDto scenicQueryDto);

    // 关联景点下拉选择器
    Result<List<SelectedVO>> querySelectedScenic();

    // 浏览操作
    Result<Void> viewOperation(Integer scenicId);

    // 查看用户收藏状况
    Result<Boolean> saveStatus(Integer scenicId);

    // 收藏操作
    Result<Void> saveOperation(Integer scenicId);

    // 查询用户收藏的景点信息
    Result<List<ScenicVO>> querySave();

}
