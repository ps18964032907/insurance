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
import com.insurance.policy.admin.domain.VehicleCoverage;
import com.insurance.policy.admin.service.IVehicleCoverageService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 险种责任信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/coverage")
public class VehicleCoverageController extends BaseController
{
    @Autowired
    private IVehicleCoverageService vehicleCoverageService;

    /**
     * 查询险种责任信息列表
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleCoverage vehicleCoverage)
    {
        startPage();
        List<VehicleCoverage> list = vehicleCoverageService.selectVehicleCoverageList(vehicleCoverage);
        return getDataTable(list);
    }

    /**
     * 导出险种责任信息列表
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:export')")
    @Log(title = "险种责任信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleCoverage vehicleCoverage) throws IOException
    {
        List<VehicleCoverage> list = vehicleCoverageService.selectVehicleCoverageList(vehicleCoverage);
        ExcelUtil<VehicleCoverage> util = new ExcelUtil<VehicleCoverage>(VehicleCoverage.class);
        util.exportExcel(response, list, "coverage");
    }

    /**
     * 获取险种责任信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehicleCoverageService.selectVehicleCoverageById(id));
    }

    /**
     * 新增险种责任信息
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:add')")
    @Log(title = "险种责任信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleCoverage vehicleCoverage)
    {
        return toAjax(vehicleCoverageService.insertVehicleCoverage(vehicleCoverage));
    }

    /**
     * 修改险种责任信息
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:edit')")
    @Log(title = "险种责任信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleCoverage vehicleCoverage)
    {
        return toAjax(vehicleCoverageService.updateVehicleCoverage(vehicleCoverage));
    }

    /**
     * 删除险种责任信息
     */
    @PreAuthorize("@ss.hasPermi('coverage:coverage:remove')")
    @Log(title = "险种责任信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleCoverageService.deleteVehicleCoverageByIds(ids));
    }
}
