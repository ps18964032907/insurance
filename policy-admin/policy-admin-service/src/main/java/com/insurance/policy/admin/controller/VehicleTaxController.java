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
import com.insurance.policy.admin.domain.VehicleTax;
import com.insurance.policy.admin.service.IVehicleTaxService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 车船税信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/tax")
public class VehicleTaxController extends BaseController
{
    @Autowired
    private IVehicleTaxService vehicleTaxService;

    /**
     * 查询车船税信息列表
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleTax vehicleTax)
    {
        startPage();
        List<VehicleTax> list = vehicleTaxService.selectVehicleTaxList(vehicleTax);
        return getDataTable(list);
    }

    /**
     * 导出车船税信息列表
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:export')")
    @Log(title = "车船税信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleTax vehicleTax) throws IOException
    {
        List<VehicleTax> list = vehicleTaxService.selectVehicleTaxList(vehicleTax);
        ExcelUtil<VehicleTax> util = new ExcelUtil<VehicleTax>(VehicleTax.class);
        util.exportExcel(response, list, "tax");
    }

    /**
     * 获取车船税信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehicleTaxService.selectVehicleTaxById(id));
    }

    /**
     * 新增车船税信息
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:add')")
    @Log(title = "车船税信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleTax vehicleTax)
    {
        return toAjax(vehicleTaxService.insertVehicleTax(vehicleTax));
    }

    /**
     * 修改车船税信息
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:edit')")
    @Log(title = "车船税信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleTax vehicleTax)
    {
        return toAjax(vehicleTaxService.updateVehicleTax(vehicleTax));
    }

    /**
     * 删除车船税信息
     */
    @PreAuthorize("@ss.hasPermi('tax:tax:remove')")
    @Log(title = "车船税信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleTaxService.deleteVehicleTaxByIds(ids));
    }
}
