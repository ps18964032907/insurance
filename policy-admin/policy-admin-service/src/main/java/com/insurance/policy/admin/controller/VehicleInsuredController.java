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
import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.service.IVehicleInsuredService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 被保险车辆信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/insured")
public class VehicleInsuredController extends BaseController
{
    @Autowired
    private IVehicleInsuredService vehicleInsuredService;

    /**
     * 查询被保险车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleInsured vehicleInsured)
    {
        startPage();
        List<VehicleInsured> list = vehicleInsuredService.selectVehicleInsuredList(vehicleInsured);
        return getDataTable(list);
    }

    /**
     * 导出被保险车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:export')")
    @Log(title = "被保险车辆信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleInsured vehicleInsured) throws IOException
    {
        List<VehicleInsured> list = vehicleInsuredService.selectVehicleInsuredList(vehicleInsured);
        ExcelUtil<VehicleInsured> util = new ExcelUtil<VehicleInsured>(VehicleInsured.class);
        util.exportExcel(response, list, "insured");
    }

    /**
     * 获取被保险车辆信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehicleInsuredService.selectVehicleInsuredById(id));
    }

    /**
     * 新增被保险车辆信息
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:add')")
    @Log(title = "被保险车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleInsured vehicleInsured)
    {
        return toAjax(vehicleInsuredService.insertVehicleInsured(vehicleInsured));
    }

    /**
     * 修改被保险车辆信息
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:edit')")
    @Log(title = "被保险车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleInsured vehicleInsured)
    {
        return toAjax(vehicleInsuredService.updateVehicleInsured(vehicleInsured));
    }

    /**
     * 删除被保险车辆信息
     */
    @PreAuthorize("@ss.hasPermi('insured:insured:remove')")
    @Log(title = "被保险车辆信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleInsuredService.deleteVehicleInsuredByIds(ids));
    }
}
