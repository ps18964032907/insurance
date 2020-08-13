package com.insurance.policy.admin.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.insurance.policy.admin.domain.VehicleCustomer;
import com.insurance.policy.admin.service.IVehicleCustomerService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 投保单客户信息（投保人，被保人，车主）Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/customer")
public class VehicleCustomerController extends BaseController
{
    @Autowired
    private IVehicleCustomerService vehicleCustomerService;

    /**
     * 查询投保单客户信息（投保人，被保人，车主）列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleCustomer vehicleCustomer)
    {
        startPage();
        List<VehicleCustomer> list = vehicleCustomerService.selectVehicleCustomerList(vehicleCustomer);
        return getDataTable(list);
    }

    /**
     * 导出投保单客户信息（投保人，被保人，车主）列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "投保单客户信息（投保人，被保人，车主）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleCustomer vehicleCustomer) throws IOException
    {
        List<VehicleCustomer> list = vehicleCustomerService.selectVehicleCustomerList(vehicleCustomer);
        ExcelUtil<VehicleCustomer> util = new ExcelUtil<VehicleCustomer>(VehicleCustomer.class);
        util.exportExcel(response, list, "customer");
    }

    /**
     * 获取投保单客户信息（投保人，被保人，车主）详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehicleCustomerService.selectVehicleCustomerById(id));
    }

    /**
     * 新增投保单客户信息（投保人，被保人，车主）
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "投保单客户信息（投保人，被保人，车主）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleCustomer vehicleCustomer)
    {
        return toAjax(vehicleCustomerService.insertVehicleCustomer(vehicleCustomer));
    }

    /**
     * 修改投保单客户信息（投保人，被保人，车主）
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "投保单客户信息（投保人，被保人，车主）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleCustomer vehicleCustomer)
    {
        return toAjax(vehicleCustomerService.updateVehicleCustomer(vehicleCustomer));
    }

    /**
     * 删除投保单客户信息（投保人，被保人，车主）
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "投保单客户信息（投保人，被保人，车主）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleCustomerService.deleteVehicleCustomerByIds(ids));
    }
}
