package com.insurance.policy.admin.controller;

import java.math.BigDecimal;
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
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.admin.service.IVehiclePolicyMainService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 保单主要信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/policy")
public class VehiclePolicyMainController extends BaseController
{
    @Autowired
    private IVehiclePolicyMainService vehiclePolicyMainService;


    /**
     * 查询保单主要信息列表
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehiclePolicyMain vehiclePolicyMain)
    {
        startPage();
        List<VehiclePolicyMain> list = vehiclePolicyMainService.selectVehiclePolicyMainList(vehiclePolicyMain);
        return getDataTable(list);
    }

    /**
     * 导出保单主要信息列表
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:export')")
    @Log(title = "保单主要信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehiclePolicyMain vehiclePolicyMain) throws IOException
    {
        List<VehiclePolicyMain> list = vehiclePolicyMainService.selectVehiclePolicyMainList(vehiclePolicyMain);
        ExcelUtil<VehiclePolicyMain> util = new ExcelUtil<VehiclePolicyMain>(VehiclePolicyMain.class);
        util.exportExcel(response, list, "policy");
    }

    /**
     * 获取保单主要信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehiclePolicyMainService.selectVehiclePolicyMainById(id));
    }

    /**
     * 新增保单主要信息
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:add')")
    @Log(title = "保单主要信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehiclePolicyMain vehiclePolicyMain)
    {
        return toAjax(vehiclePolicyMainService.insertVehiclePolicyMain(vehiclePolicyMain));
    }

    /**
     * 修改保单主要信息
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:edit')")
    @Log(title = "保单主要信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehiclePolicyMain vehiclePolicyMain)
    {
        return toAjax(vehiclePolicyMainService.updateVehiclePolicyMain(vehiclePolicyMain));
    }

    /**
     * 删除保单主要信息
     */
    @PreAuthorize("@ss.hasPermi('policy:policy:remove')")
    @Log(title = "保单主要信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehiclePolicyMainService.deleteVehiclePolicyMainByIds(ids));
    }
}
