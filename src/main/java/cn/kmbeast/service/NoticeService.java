package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Notice;

import java.util.List;

public interface NoticeService {

    // 增加
    Result<Void> save(Notice notice);

    // 删除
    Result<Void> batchDelete(List<Integer> ids);

    // 修改
    Result<Void> update(Notice notice);

    // 查询
    Result<List<Notice>> query(NoticeQueryDto noticeQueryDto);

}
