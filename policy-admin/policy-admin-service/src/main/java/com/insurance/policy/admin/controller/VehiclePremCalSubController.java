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
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import com.insurance.policy.admin.service.IVehiclePremCalSubService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 保费计算附加信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/calcsub")
public class VehiclePremCalSubController extends BaseController
{
    @Autowired
    private IVehiclePremCalSubService vehiclePremCalSubService;

    /**
     * 查询保费计算附加信息列表
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehiclePremCalSub vehiclePremCalSub)
    {
        startPage();
        List<VehiclePremCalSub> list = vehiclePremCalSubService.selectVehiclePremCalSubList(vehiclePremCalSub);
        return getDataTable(list);
    }

    /**
     * 导出保费计算附加信息列表
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:export')")
    @Log(title = "保费计算附加信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehiclePremCalSub vehiclePremCalSub) throws IOException
    {
        List<VehiclePremCalSub> list = vehiclePremCalSubService.selectVehiclePremCalSubList(vehiclePremCalSub);
        ExcelUtil<VehiclePremCalSub> util = new ExcelUtil<VehiclePremCalSub>(VehiclePremCalSub.class);
        util.exportExcel(response, list, "calcsub");
    }

    /**
     * 获取保费计算附加信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vehiclePremCalSubService.selectVehiclePremCalSubById(id));
    }

    /**
     * 新增保费计算附加信息
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:add')")
    @Log(title = "保费计算附加信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehiclePremCalSub vehiclePremCalSub)
    {
        return toAjax(vehiclePremCalSubService.insertVehiclePremCalSub(vehiclePremCalSub));
    }

    /**
     * 修改保费计算附加信息
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:edit')")
    @Log(title = "保费计算附加信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehiclePremCalSub vehiclePremCalSub)
    {
        return toAjax(vehiclePremCalSubService.updateVehiclePremCalSub(vehiclePremCalSub));
    }

    /**
     * 删除保费计算附加信息
     */
    @PreAuthorize("@ss.hasPermi('calcsub:calcsub:remove')")
    @Log(title = "保费计算附加信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehiclePremCalSubService.deleteVehiclePremCalSubByIds(ids));
    }
}
