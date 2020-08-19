package com.insurance.policy.admin.controller;

import cn.hutool.core.util.RandomUtil;
import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.admin.service.PolicyService;
import com.insurance.policy.admin.util.RedisUtils;
import com.insurance.policy.admin.util.ValidationUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("combined")
public class PolicyController extends BaseController {


    @Autowired
    private PolicyService policyService;
    @Autowired
    private RedisUtils redisUtils;


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

        //获取随机数的id
        Long id = combinedPolicy.getCommercialPolicy().getVehiclePolicyMain().getId();



        VehiclePolicyMain vehiclePolicyMain = combinedPolicy.getCommercialPolicy().getVehiclePolicyMain();
        VehiclePolicyMain vehiclePolicyMain1 = combinedPolicy.getCompulsoryPolicy().getVehiclePolicyMain();

        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(vehiclePolicyMain);
        ValidationUtil.ValidResult validResult1 = ValidationUtil.validateBean(vehiclePolicyMain1);



        if (validResult.hasErrors() ) {
            return AjaxResult.error(validResult.getErrors());
        }
        if(validResult1.hasErrors()){
            return AjaxResult.error(validResult1.getErrors());
        }

        if (!redisUtils.hasKey(id+"")) {
            return AjaxResult.error("请勿重复提交");
        }
        redisUtils.del(id+"");

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
        List<VehiclePolicyMain> list = policyService.queryUnderwriting();

        /**
         * 模拟返回的对象
         */
        return getDataTable(list);

    }

    @GetMapping("getRedisRandom")
    public String getRedisRandom(){
        String s = "1"+RandomUtil.randomNumbers(12);
        redisUtils.set(s,s,600);
        return s;
    }

    /**
     * 核保保单
     *
     * @param id
     * @return
     */
    @PostMapping("underwriting")
    public AjaxResult underwriting(Long id) {
        int underwriting = policyService.underwriting(id);
        if(underwriting == 1){
            return AjaxResult.success("成功");
        }
        return toAjax(0);
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
