package cn.kmbeast.mapper;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.VendorQueryDto;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.VendorVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VendorMapper {
    // 增加服务商
    public int save(Vendor vendor);

    // 删除服务商
    public int batchDelete(@Param("ids") List<Integer> ids);

    // 修改服务商信息
    public int update(Vendor vendor);

    // 查询服务商信息
    public  List<VendorVO> query(VendorQueryDto vendorQueryDto);

    // 查询服务商条数
    int queryCount(VendorQueryDto vendorQueryDto);

    // 根据不为空的信息查询服务商信息
    Vendor getByActive(Vendor vendor);
}
