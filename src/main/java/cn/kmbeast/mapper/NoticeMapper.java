package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 添加
    int save(Notice notice);

    // 删除
    int batchDelete(@Param("ids") List<Integer> ids);

    // 修改
    int update(Notice notice);

    // 分页查询结果
    List<Notice> query(NoticeQueryDto noticeQueryDto);

    // 查询总条数
    int queryTotal(NoticeQueryDto noticeMapper);
}
