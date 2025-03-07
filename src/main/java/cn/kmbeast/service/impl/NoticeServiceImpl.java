package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.NoticeMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Notice;
import cn.kmbeast.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    // 增加
    @Override
    public Result<Void> save(Notice notice) {
        notice.setCreateTime(LocalDateTime.now());
        int row = noticeMapper.save(notice);
        if(row > 0){
            return ApiResult.success("添加成功！");
        }
        return ApiResult.error("添加失败！");
    }

    // 删除
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = noticeMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改
    @Override
    public Result<Void> update(Notice notice) {
        int row = noticeMapper.update(notice);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 查询
    @Override
    public Result<List<Notice>> query(NoticeQueryDto noticeQueryDto) {
        List<Notice> result = noticeMapper.query(noticeQueryDto);
        int total = noticeMapper.queryTotal(noticeQueryDto);
        return ApiResult.success(result, total);
    }
}
