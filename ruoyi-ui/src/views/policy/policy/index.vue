<template>
  <div class="app-container">
   <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保单/投保单号" prop="policyNo">
        <el-input
          v-model="queryParams.policyNo"
          placeholder="请输入保单/投保单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联的保单/投保单主键" prop="associatedPolicyId">
        <el-input
          v-model="queryParams.associatedPolicyId"
          placeholder="请输入关联的保单/投保单主键"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="归属经办人id" prop="belongToHanderId">
        <el-input
          v-model="queryParams.belongToHanderId"
          placeholder="请输入归属经办人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="归属机构id" prop="belongToOrgId">
        <el-input
          v-model="queryParams.belongToOrgId"
          placeholder="请输入归属机构id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务来源代码" prop="businessSourceCode">
        <el-select v-model="queryParams.businessSourceCode" placeholder="请选择业务来源代码" clearable size="small">
          <el-option
            v-for="dict in businessSourceCodeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="保单/投保单状态" prop="policyStatus">
        <el-select v-model="queryParams.policyStatus" placeholder="请选择保单/投保单状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="保单生效日期" prop="effectiveDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.effectiveDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择保单生效日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="保单终止日期" prop="expiryDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.expiryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择保单终止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="标准保费" prop="standPremium">
        <el-input
          v-model="queryParams.standPremium"
          placeholder="请输入标准保费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应缴纳保费" prop="duePremium">
        <el-input
          v-model="queryParams.duePremium"
          placeholder="请输入应缴纳保费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本号" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="insertTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.insertTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否是交强险：0-商业险 1-交强险" prop="compulsoryPolicy">
        <el-input
          v-model="queryParams.compulsoryPolicy"
          placeholder="请输入是否是交强险：0-商业险 1-交强险"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新保单录入</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['policy:policy:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['policy:policy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['policy:policy:export']"
        >导出</el-button>
      </el-col> -->
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="policyList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="保单号" align="center" prop="policyNo" />
      <!-- <el-table-column label="关联的保单/投保单主键" align="center" prop="associatedPolicyId" /> -->
      <!-- <el-table-column label="归属经办人id" align="center" prop="belongToHanderId" /> -->
      <!-- <el-table-column label="归属机构id" align="center" prop="belongToOrgId" /> -->
      <el-table-column label="业务来源" align="center" prop="businessSourceCode" :formatter="businessSourceCodeFormat" />
      <!-- <el-table-column label="保单/投保单状态" align="center" prop="policyStatus" /> -->
      <el-table-column label="保单生效日期" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保单终止日期" align="center" prop="expiryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expiryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="标准保费" align="center" prop="standPremium" /> -->
      <el-table-column label="应缴纳保费" align="center" prop="duePremium" />
      <!-- <el-table-column label="版本号" align="center" prop="version" /> -->
<!--      <el-table-column label="创建时间" align="center" prop="insertTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insertTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="是否是交强险：0-商业险 1-交强险" align="center" prop="compulsoryPolicy" /> -->
      <!-- <el-table-column label="电子保单文件地址" align="center" prop="epolicyUrl" /> -->
      <!--
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >提交核保</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['policy:policy:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog
      :title="policyDialogTitle"
      :visible.sync="policyDetailDialogVisible"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      width="80%"
      @close="editPolicyFormClose">
      <el-form
        :model="editPolicyForm"
        :rules="editPolicyFormRules"
        ref="editPolicyFormRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-collapse>
          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              基本信息
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="查询码(交)">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePremCalSub.proposalQueryNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="预确认码(交)">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePremCalSub.proposalPreConfirmNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="确认码(交)">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePremCalSub.proposalConfirmNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="查询码(商)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehiclePremCalSub.proposalQueryNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="预确认码(商)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehiclePremCalSub.proposalPreConfirmNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="确认码(商)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehiclePremCalSub.proposalConfirmNo"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="标准保费(商)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehiclePolicyMain.standPremium"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="应缴保费(商)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehiclePolicyMain.duePremium"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="标准保费(交)">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePolicyMain.standPremium"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="业务来源">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehiclePolicyMain.businessSourceCode"
                    placeholder="请选择业务来源"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in businessSourceCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="应缴保费(交)">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePolicyMain.duePremium"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="车船税">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.totalTaxAmount"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-collapse-item>

          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              车辆信息
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="车架号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vin"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="发动机号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.engineNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="车牌号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.licenseNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="厂牌型号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vehicleFactoryName"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="初登日期">
                  <el-date-picker
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vehicleFirstRegDate"
                    type="date"
                    placeholder="选择日期"
                    :disabled="isDisabled"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="新车价">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.newVehiclePrice"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="排量(升)">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.displacement"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="功率">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.power"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="整备质量">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vehicleQuality"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="核定载客数">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.approvedSeatCount"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="号牌种类">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.licenseType"
                    placeholder="请选择号牌种类"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in licenseTypeCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />

                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="车辆种类">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vehicleKind"
                    placeholder="请选择车辆种类"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in vehicleKindCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="使用性质">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.vehicleUseNature"
                    placeholder="请选择使用性质"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in useNatureCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="燃料种类">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleInsured.gasType"
                    placeholder="请选择燃料种类"
                    :disabled="isDisabled"
                  >
                <el-option
                  v-for="dict in gasTypeCodeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="6">
                <el-form-item label="已上传照片">
                  <el-select v-model="img" placeholder="已上传照片列表" @change="showImage($event)">
                    <el-option
                      :label="item.name"
                      :value="item.url"
                      :key="item.url"
                      v-for="item in imageList"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-upload
                  class="upload-demo"
                  :show-file-list="false"
                  :limit="5"
                  :on-success="onFileUploadSuccess"
                  :on-error="onFileUploadError"
                  :action="uploadServerAddress"
                >
                  <div v-if="!isDisabled">
                    <el-button type="primary">选照片</el-button>
                  </div>
                </el-upload>
              </el-col>
            </el-row>
          </el-collapse-item>

          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              车主
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].customerType"
                    placeholder="请选择客户类型"
                    :disabled="isDisabled"
                  >
                    <el-option
                      v-for="dict in customerTypeCodeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictLabel"
                      :value="dict.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].idType"
                    placeholder="请选择证件类型"
                    :disabled="isDisabled"
                  >
                    <el-option
                      v-for="dict in idTypeCodeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictLabel"
                      :value="dict.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="证件号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].idNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户名称">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].userName"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="性别">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].gender"
                    placeholder="请选择性别"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in sexCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="手机">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].cellphone"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="联系地址">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].address"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="邮箱">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[0].email"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              投保人
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].customerType"
                    placeholder="请选择客户类型"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in customerTypeCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].idType"
                    placeholder="请选择证件类型"
                    :disabled="isDisabled"
                  >
                  <el-option
                    v-for="dict in idTypeCodeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="证件号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].idNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户名称">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].userName"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="性别">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].gender"
                    placeholder="请选择性别"
                    :disabled="isDisabled"
                  >
                    <el-option
                      v-for="dict in sexCodeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictLabel"
                      :value="dict.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="手机">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].cellphone"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="联系地址">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].address"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="邮箱">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[1].email"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>

            </el-row>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              被保人
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].customerType"
                    placeholder="请选择客户类型"
                    :disabled="isDisabled"
                  >
          <el-option
            v-for="dict in customerTypeCodeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />

                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件类型">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].idType"
                    placeholder="请选择证件类型"
                    :disabled="isDisabled"
                  >
          <el-option
            v-for="dict in idTypeCodeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />

                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="证件号">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].idNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="客户名称">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].userName"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="性别">
                  <el-select
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].gender"
                    placeholder="请选择性别"
                    :disabled="isDisabled"
                  >
          <el-option
            v-for="dict in sexCodeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="手机">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].cellphone"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="联系地址">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].address"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="邮箱">
                  <el-input
                    v-model="editPolicyForm.commercialPolicy.vehicleCustomers[2].email"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
<!--              <el-col :span="8">
                <el-form-item label="同投保人">
                  <el-switch
                    v-model="sameAsHolder"
                    active-text="是"
                    inactive-text="否"
                    @change="changeSameAsHolder($event)"
                    :disabled="isDisabled"
                  ></el-switch>
                </el-form-item>
              </el-col> -->
            </el-row>
          </el-collapse-item>

          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              交强险
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="起始日期">
                  <el-date-picker
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePolicyMain.effectiveDate"
                    type="date"
                    placeholder="选择日期"
                    :disabled="isDisabled"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="终止日期">
                  <el-date-picker
                    v-model="editPolicyForm.compulsoryPolicy.vehiclePolicyMain.expiryDate"
                    type="date"
                    placeholder="选择日期"
                    :disabled="isDisabled"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-table :data="editPolicyForm.compulsoryPolicy.vehicleCoverages" style="width: 100%">
                  <el-table-column prop="name" label="责任名称" width="160"></el-table-column>
                  <el-table-column label="保额(元)" width="260">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.sumInsured" :disabled="isDisabled"></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column label="标准保费(元)" width="200">
                    <template slot-scope="scope">{{scope.row.stdPrem}}</template>
                  </el-table-column>
                  <el-table-column label="应缴保费(元)">
                    <template slot-scope="scope">{{scope.row.duePrem}}</template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </el-collapse-item>

          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              商业险
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="起始日期">
                  <el-date-picker
                    v-model="editPolicyForm.commercialPolicy.vehiclePolicyMain.effectiveDate"
                    type="date"
                    placeholder="选择日期"
                    :disabled="isDisabled"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="终止日期">
                  <el-date-picker
                    v-model="editPolicyForm.commercialPolicy.vehiclePolicyMain.expiryDate"
                    type="date"
                    placeholder="选择日期"
                    :disabled="isDisabled"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-table :data="editPolicyForm.commercialPolicy.vehicleCoverages" style="width: 100%">
                  <el-table-column label="#" width="40"></el-table-column>
                  <el-table-column prop="name" label="责任名称" width="160"></el-table-column>
                  <el-table-column label="保额(元)" width="260">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.sumInsured" :disabled="isDisabled"></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column label="标准保费(元)" width="200">
                    <template slot-scope="scope">{{scope.row.stdPrem}}</template>
                  </el-table-column>
                  <el-table-column label="应缴保费(元)">
                    <template slot-scope="scope">{{scope.row.duePrem}}</template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <i class="header-icon el-icon-d-arrow-left"></i>
              车船税
              <i class="header-icon el-icon-d-arrow-right"></i>
            </template>

            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="缴税人识别号">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.taxPayerNo"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="缴税人名称">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.taxPayerName"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="联系地址">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.address"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="电话">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.tel"
                    :disabled="isDisabled"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="标准税款">
                  <el-input v-model="editPolicyForm.compulsoryPolicy.vehicleTax.stdTax" disabled></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="当年税款">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.currentTax"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="1">
              <el-col :span="8">
                <el-form-item label="往年欠款">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.previousTax"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="滞纳金">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.overdueFee"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="应缴总金额">
                  <el-input
                    v-model="editPolicyForm.compulsoryPolicy.vehicleTax.totalTaxAmount"
                    disabled
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-collapse-item>
        </el-collapse>
      </el-form>
      <span slot="footer" class="dialog-footer" >
        <el-button type="primary" @click="editPolicy">计算并保存</el-button>
        <el-button @click="policyDetailDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { listPolicy, getPolicy, delPolicy, addPolicy, updatePolicy } from "@/api/policy/policy";

export default {
  name: "Policy",
  data() {
    return {
      policyDialogTitle:'保单录入',
      policyDetailDialogVisible:false,
      isDisabled:false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 保单主要信息表格数据
      policyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 业务来源代码字典
      businessSourceCodeOptions: [],
      customerTypeCodeOptions:[],
      gasTypeCodeOptions:[],
      idTypeCodeOptions:[],
      importTypeCodeOptions:[],
      licenseTypeCodeOptions:[],
      useNatureCodeOptions:[],
      vehicleKindCodeOptions:[],
      sexCodeOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyNo: null,
        associatedPolicyId: null,
        belongToHanderId: null,
        belongToOrgId: null,
        businessSourceCode: null,
        policyStatus: null,
        effectiveDate: null,
        expiryDate: null,
        standPremium: null,
        duePremium: null,
        version: null,
        insertTime: null,
        compulsoryPolicy: null,
        epolicyUrl: null
      },
      // 表单参数
      form: {},
      // commercialCoverageList: [
      //   {
      //     productionCoverageCode: "A1",
      //     name: "车辆损失险",
      //     sumInsured: "",
      //     stdPrem: "",
      //     duePrem: ""
      //   },
      //   {
      //     productionCoverageCode: "A3",
      //     name: "第三者责任险",
      //     sumInsured: "",
      //     stdPrem: "",
      //     stdPrem: ""
      //   },
      //   {
      //     productionCoverageCode: "A2",
      //     name: "全车盗抢险",
      //     sumInsured: "",
      //     stdPrem: "",
      //     duePrem: ""
      //   }
      // ], //商业险责任列表
      // compulsoryCoverageList: [
      //   {
      //     productionCoverageCode: "Z1",
      //     name: "交通强制责任保险",
      //     sumInsured: 122000,
      //     stdPrem: "",
      //     duePrem: ""
      //   }
      // ],
      editPolicyForm: {
        commercialPolicy: {
          vehiclePolicyMain: {
            id: "",
            policyNo: "",
            associatedPolicyId: "",
            belongToHanderId: "",
            belongToOrgId: "",
            compulsory: "0",
            businessSourceCode: "1",
            policyStatus: "1",
            effectiveDate: "2020-09-02",
            expiryDate: "2021-09-01",
            standPremium: "",
            duePremium: "",
            version: ""
          },
          vehicleCoverages: [
            {
              id: "",
              policyId: "",
              productionCoverageCode: "A1",
              name: "车辆损失险",
              standPremium: 0,
              sumInsured: 300000,
              duePremium: 0,
              version: 1
            },
            {
              id: "",
              policyId: "",
              productionCoverageCode: "A2",
              name: "全车盗抢险",
              standPremium: 0,
              sumInsured: 300000,
              duePremium: 0,
              version: 1
            },
            {
              id: "",
              policyId: "",
              productionCoverageCode: "A3",
              name: "第三者责任险",
              standPremium: "",
              sumInsured: 1000000,
              duePremium: 0,
              version: 1
            }
          ],
          vehiclePremCalSub: {
            id: "",
            policyId: "",
            proposalQueryNo: "",
            proposalPreConfirmNo: "",
            proposalConfirmNo: "",
            proposalQueryNoExpireDate: "",
            proposalPreConfirmNoExpireDate: "",
            ncdRate: "",
            trafficViolationRate: "",
            uwRate: "",
            platformBenchRiskPrem: "",
            version: ""
          },
          vehicleCustomers: [
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "1",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            },
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "2",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            },
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "3",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            }
          ],
          vehicleInsured: {
            id: "",
            policyId: "",
            licenseNo: "沪A29832",
            licenseType: "1",
            importType: "1",
            vehicleKind: "1",
            vehicleUseNature: "1",
            vehicleFirstRegDate: "2019-01-09",
            displacement: "1",
            approvedQuality: "500",
            approvedSeatCount: "5",
            vin: "HHS38888389999888",
            engineNo: "332223",
            gasType: "1",
            actualPrice: "200000",
            newVehiclePrice: "300000",
            vehicleFactoryName: "奔驰E300豪华版2019款",
            industryVehicleCode: "28238892983",
            newVehicle: "",
            power: "2",
            realUserYear: "1",
            vehicleQuality: "1100",
            version: "",
            imgUrls: ""
          }
        },
        compulsoryPolicy: {
          vehiclePolicyMain: {
            id: "",
            policyNo: "",
            associatedPolicyId: "",
            belongToHanderId: "",
            belongToOrgId: "",
            compulsory: "1",
            businessSourceCode: "",
            policyStatus: "1",
            effectiveDate: "2020-09-02",
            expiryDate: "2021-09-01",
            standPremium: "",
            duePremium: "",
            version: ""
          },
          vehicleCoverages: [
            {
              id: "",
              policyId: "",
              productionCoverageCode: "Z1",
              name: "交通强制责任保险",
              standPremium: "",
              sumInsured: 122000,
              duePremium: "",
              version: ""
            }
          ],
          vehiclePremCalSub: {
            id: "",
            policyId: "",
            proposalQueryNo: "",
            proposalPreConfirmNo: "",
            proposalConfirmNo: "",
            proposalQueryNoExpireDate: "",
            proposalPreConfirmNoExpireDate: "",
            ncdRate: "",
            trafficViolationRate: "",
            uwRate: "",
            platformBenchRiskPrem: "",
            version: ""
          },
          vehicleTax: {
            id: "",
            policyId: "",
            taxPayerNo: "200020202002",
            taxPayerName: "李四",
            stdTax: "",
            currentTax: "",
            previousTax: "",
            overdueFee: "",
            address: "浦东新区蜗牛学院",
            tel: "18900001111",
            totalTaxAmount: "",
            version: ""
          },
          vehicleCustomers: [
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "1",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            },
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "2",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            },
            {
              id: "",
              policyId: "",
              idNo: "220181198602178297",
              idType: "1",
              userName: "张三",
              userRoleType: "3",
              cellphone: "15900187761",
              address: "上海市浦东新区蜗牛学院",
              customerType: "1",
              email: "sss@woniu.com",
              gender: "1",
              relation: "1",
              version: ""
            }
          ],
          vehicleInsured: {
            id: "",
            policyId: "",
            licenseNo: "沪A29832",
            licenseType: "1",
            importType: "1",
            vehicleKind: "1",
            vehicleUseNature: "1",
            vehicleFirstRegDate: "2019-01-09",
            displacement: "1",
            approvedQuality: "500",
            approvedSeatCount: "5",
            vin: "HHS38888389999888",
            engineNo: "332223",
            gasType: "1",
            actualPrice: "200000",
            newVehiclePrice: "300000",
            vehicleFactoryName: "",
            industryVehicleCode: "28238892983",
            newVehicle: "",
            power: "2",
            realUserYear: "1",
            vehicleQuality: "1100",
            version: "",
            imgUrls: ""
          }
        }
      },
      img: "",
      imageList:[],
      uploadServerAddress: '/upload/img',
      // 表单校验
      editPolicyFormRules: {
        // compulsoryPolicy: [
        //   { required: true, message: "是否是交强险：0-商业险 1-交强险不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();

    this.getDicts("policy_sale_source").then(response => {
      this.businessSourceCodeOptions = response.data;
    });
    this.getDicts("policy_custom_type").then(response => {
      this.customerTypeCodeOptions = response.data;
    });
    this.getDicts("vehicle_gas_type").then(response => {
      this.gasTypeCodeOptions = response.data;
    });
    this.getDicts("id_type").then(response => {
      this.idTypeCodeOptions = response.data;
    });
    this.getDicts("import_type").then(response => {
      this.importTypeCodeOptions = response.data;
    });
    this.getDicts("license_type").then(response => {
      this.licenseTypeCodeOptions = response.data;
    });
    this.getDicts("vehicle_use_nature").then(response => {
      this.useNatureCodeOptions = response.data;
    });
    this.getDicts("vehicle_kind").then(response => {
      this.vehicleKindCodeOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexCodeOptions = response.data;
    });
  },
  methods: {

    //文件上传成功回调函数
    onFileUploadSuccess(response, file, fileList) {
      if (response) {
        this.$message.success("图片上传成功");
        let img = { name: file.name, url: response.result };
        if (this.imageList) {
          this.imageList.push(img);
        }
      }
      //console.log(response);
      //console.log(file);
      //console.log(fileList);
    },
    //文件上传失败回调函数
    onFileUploadError(err, file, fileList) {
      console.log("file upload error=>" + err);
      this.$message.error("文件上传失败");
    },
    /** 查询保单主要信息列表 */
    getList() {
      this.loading = true;
      listPolicy(this.queryParams).then(response => {
        this.policyList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(response);
      });
    },
    editPolicy() {
      addPolicy(this.editPolicyForm)
        .then(res => {
          if (res.code !== 200) {
            this.$message.error("提交失败");
          } else {
            this.policyDetailDialogVisible = false;
            this.$message.success("提交成功");

          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("服务端异常，请联系管理员");
        });
    },
    // 业务来源代码字典翻译
    businessSourceCodeFormat(row, column) {
      return this.selectDictLabel(this.businessSourceCodeOptions, row.businessSourceCode);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    editPolicyFormClose() {
      this.cancel();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        policyNo: null,
        associatedPolicyId: null,
        belongToHanderId: null,
        belongToOrgId: null,
        businessSourceCode: null,
        policyStatus: "0",
        effectiveDate: null,
        expiryDate: null,
        standPremium: null,
        duePremium: null,
        version: null,
        insertTime: null,
        updateTime: null,
        compulsory: null,
        epolicyUrl: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {

      // alert('ass')
      this.policyDetailDialogVisible = true;

    },
    /** 提交核保 */
    handleUpdate(row) {
      const id = row.id || this.ids
      updatePolicy(id).then(response => {
            if (response.code === 200) {
              this.msgSuccess("提交成功");
              this.open = false;
              this.getList();
            }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePolicy(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPolicy(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除保单主要信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPolicy(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('policy/policy/export', {
        ...this.queryParams
      }, `policy_policy.xlsx`)
    }
  }
};
</script>
