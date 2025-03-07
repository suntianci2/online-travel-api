package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.VendorQueryDto;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.VendorVO;

import java.util.List;

public interface VendorService {
    // 增加服务商
    public Result<Void> save(Vendor vendor);

    // 删除服务商
    public Result<Void> batchDelete(List<Integer> ids);

    // 修改服务商信息
    public Result<Void> update(Vendor vendor);

    // 查询服务商信息
    public  Result<List<VendorVO>> query(VendorQueryDto vendorQueryDto);
}
