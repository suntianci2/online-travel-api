package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.VendorMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.VendorQueryDto;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.VendorVO;
import cn.kmbeast.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorMapper vendorMapper;


    // 增加服务商
    @Override
    public Result<Void> save(Vendor vendor) {

        // 判断当前服务商名称是否已经存在
        Vendor tempNameVendor = Vendor.builder().name(vendor.getName()).build();
        Vendor nameExist = vendorMapper.getByActive(tempNameVendor);
        if(nameExist != null){
            return ApiResult.error("服务商名称已存在");
        }

        // 如果userId为null，设置userId为当前操作者的id
        if (vendor.getUserId() == null){
            Integer userId = LocalThreadHolder.getUserId();
            vendor.setUserId(userId);
        }

        // 一个用户只能有一条供应商记录
        Vendor tempUserVendor = Vendor.builder().userId(vendor.getUserId()).build();
        Vendor UserVendorExist = vendorMapper.getByActive(tempUserVendor);
        if(UserVendorExist != null){
            return ApiResult.error("该用户已有供应商数据，请勿重复申请！");
        }

        // 如果状态数据为空，设置初始状态
        if(vendor.getIsAudit() == null){
            vendor.setIsAudit(false);
        }
        if(vendor.getStatus() == null){
            vendor.setStatus(true);
        }

        // 设置创建时间为当前
        vendor.setCreateTime(LocalDateTime.now());

        int row = vendorMapper.save(vendor);
        if(row > 0){
            return ApiResult.success("添加成功");
        }
        return ApiResult.error("添加失败");
    }

    // 删除服务商
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        int row = vendorMapper.batchDelete(ids);
        if(row > 0){
            return ApiResult.success("删除成功！");
        }
        return ApiResult.error("删除失败！");
    }

    // 修改服务商
    @Override
    public Result<Void> update(Vendor vendor) {
        int row = vendorMapper.update(vendor);
        if(row > 0){
            return ApiResult.success("修改成功！");
        }
        return ApiResult.error("修改失败！");
    }

    // 查询服务商
    @Override
    public Result<List<VendorVO>> query(VendorQueryDto vendorQueryDto) {
        List<VendorVO> vendorData = vendorMapper.query(vendorQueryDto);
        int count = vendorMapper.queryCount(vendorQueryDto);
        return ApiResult.success(vendorData, count);
    }
}
