package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.VendorQueryDto;
import cn.kmbeast.pojo.entity.Vendor;
import cn.kmbeast.pojo.vo.VendorVO;
import cn.kmbeast.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // 增加服务商
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Vendor vendor){
        return vendorService.save(vendor);
    }

    // 删除服务商
    @PostMapping("/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids){
        return vendorService.batchDelete(ids);
    }

    // 修改服务商信息
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Vendor vendor){
        return vendorService.update(vendor);
    }

    // 查询服务商信息
    // @Pager：处理页码信息
    @Pager
    @PostMapping("/query")
    public Result<List<VendorVO>> query(@RequestBody VendorQueryDto vendorQueryDto){
        Result<List<VendorVO>> query = vendorService.query(vendorQueryDto);
        return query;
    }

    // 查询指定用户id的服务商信息
    @PostMapping("/queryUser")
    public Result<List<VendorVO>> queryUser(){
        VendorQueryDto vendorQueryDto = new VendorQueryDto();

        // TODO: 使用**用户身份支持器**取得当前操作者的用户id，并且设置上查询参数
        vendorQueryDto.setUserId(LocalThreadHolder.getUserId());

        return vendorService.query(vendorQueryDto);
    }



}
