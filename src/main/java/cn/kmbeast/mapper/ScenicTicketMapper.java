package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ScenicCategoryQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ScenicTicketQueryDto;
import cn.kmbeast.pojo.entity.ScenicCategory;
import cn.kmbeast.pojo.entity.ScenicTicket;
import cn.kmbeast.pojo.vo.ScenicTicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicTicketMapper {
    /**
     * 增加门票
     * @param scenicTicket    待增加的信息
     * @return 受影响行数
     */
    public int save(ScenicTicket scenicTicket);

    /**
     * 删除门票
     * @param ids   待删除的门票信息的id列表
     * @return  受影响行数
     */
    public int batchDelete(@Param("ids") List<Integer> ids);

    /**
     * 修改门票
     * @param scenicTicket 待修改的信息
     * @return  受影响行数
     */
    public int update(ScenicTicket scenicTicket);

    /**
     * 查询门票
     * @param scenicTicketQueryDto    封装了查询相关信息的类
     * @return  查询结果
     */
    public List<ScenicTicketVO> query(ScenicTicketQueryDto scenicTicketQueryDto);

    /**
     * 查询满足条件的总条数，用于分页查询
     * @param scenicTicketQueryDto    封装了查询相关信息的类
     * @return  查询结果
     */
    int queryTotal(ScenicTicketQueryDto scenicTicketQueryDto);

    // 查询指定景点列表的全部门票
    List<ScenicTicketVO> queryScenicTickets(@Param("scenicIds")List<Integer> scenicIds);
    
}
