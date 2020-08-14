package com.insurance.policy.admin.controller;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.CommercialPolicy;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.admin.service.PolicyService;
import com.insurance.policy.admin.util.ValidationUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("combined")
public class PolicyController extends BaseController {


    @Autowired
    private PolicyService policyService;


    @GetMapping("test")
    public AjaxResult test(@RequestBody VehiclePolicyMain o) {
        return AjaxResult.success();
    }

    @GetMapping("policy/{id}")
    public AjaxResult getCombinedPolicy(@PathVariable("id") Long id) {
        ComBinedPolicy combinedPolicy = policyService.getCombinedPolicy(id);
        return AjaxResult.success(combinedPolicy);
    }

    @GetMapping("policy/list")
    public TableDataInfo list() {
        startPage();
        List<ComBinedPolicy> list = policyService.selectVehiclePolicyList();
        return getDataTable(list);
    }

    /**
     * 计算保费
     *
     * @param combinedPolicy
     * @return
     */
    @PostMapping("policy")
    public AjaxResult add(@RequestBody ComBinedPolicy combinedPolicy) {
        VehiclePolicyMain vehiclePolicyMain = combinedPolicy.getCommercialPolicy().getVehiclePolicyMain();
        VehiclePolicyMain vehiclePolicyMain1 = combinedPolicy.getCompulsoryPolicy().getVehiclePolicyMain();

        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(vehiclePolicyMain);
        ValidationUtil.ValidResult validResult1 = ValidationUtil.validateBean(vehiclePolicyMain1);
        System.out.println("aaa");
        if (validResult.hasErrors() || validResult1.hasErrors()) {
            System.out.println("aaa");
            return toAjax(0);
        }

        return toAjax(policyService.create(combinedPolicy));
    }


    /**
     * 查询待核保的保单列表
     * policystatus=“2” 代表等待核保状态
     *
     * @param
     * @return
     */
    @GetMapping("underwriting/list")
    public TableDataInfo queryUnderwriting() {
        startPage();
        System.out.println("aaaaaaaaaa");
        List<VehiclePolicyMain> list = policyService.queryUnderwriting();

        /**
         * 模拟返回的对象
         */
        return getDataTable(list);

    }

    /**
     * 核保保单
     *
     * @param id
     * @return
     */
    @PostMapping("underwriting")
    public AjaxResult underwriting(Long id) {
        return toAjax(policyService.underwriting(id));
    }


    /**
     * 查询待收费的保单列表 policyStatus=”3“代表待收费状态
     *
     * @return
     */
    @GetMapping("collect/list")
    public TableDataInfo queryCollect() {
        startPage();
        List<VehiclePolicyMain> list = policyService.queryCollect();
        /**
         * 模拟返回的对象
         */
        list = new ArrayList<>();
        VehiclePolicyMain vehiclePolicyMain = new VehiclePolicyMain();
        vehiclePolicyMain.setAssociatedPolicyId(3L);
        vehiclePolicyMain.setId(4L);
        vehiclePolicyMain.setEffectiveDate(new Date());
        vehiclePolicyMain.setExpiryDate(new Date());
        vehiclePolicyMain.setPolicyNo("CPL00992992");
        vehiclePolicyMain.setPolicyStatus("3");
        vehiclePolicyMain.setBusinessSourceCode("1");

        VehiclePolicyMain vehiclePolicyMain1 = new VehiclePolicyMain();
        vehiclePolicyMain1.setAssociatedPolicyId(4L);
        vehiclePolicyMain1.setId(3L);
        vehiclePolicyMain1.setPolicyStatus("3");
        vehiclePolicyMain1.setEffectiveDate(new Date());
        vehiclePolicyMain1.setExpiryDate(new Date());
        vehiclePolicyMain1.setPolicyNo("CCL00992992");
        vehiclePolicyMain1.setBusinessSourceCode("1");

        list.add(vehiclePolicyMain);
        list.add(vehiclePolicyMain1);


        return getDataTable(list);

    }

    /**
     * 收费Api
     *
     * @param id
     * @return
     */
    @PostMapping("collect")
    public AjaxResult collect(Long id) {
        return toAjax(policyService.collect(id));
    }
}
