<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保单/投保单主键" prop="policyId">
        <el-input
          v-model="queryParams.policyId"
          placeholder="请输入保单/投保单主键"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="号牌号码" prop="licenseNo">
        <el-input
          v-model="queryParams.licenseNo"
          placeholder="请输入号牌号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="号牌种类" prop="licenseType">
        <el-select v-model="queryParams.licenseType" placeholder="请选择号牌种类" clearable size="small">
          <el-option
            v-for="dict in licenseTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆种类代码" prop="vehicleKind">
        <el-select v-model="queryParams.vehicleKind" placeholder="请选择车辆种类代码" clearable size="small">
          <el-option
            v-for="dict in vehicleKindOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆使用性质代码" prop="vehicleUseNature">
        <el-select v-model="queryParams.vehicleUseNature" placeholder="请选择车辆使用性质代码" clearable size="small">
          <el-option
            v-for="dict in vehicleUseNatureOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆初次登记日期" prop="vehicleFirstRegDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.vehicleFirstRegDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择车辆初次登记日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="排量" prop="displacement">
        <el-input
          v-model="queryParams.displacement"
          placeholder="请输入排量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="核定载客数量" prop="approvedSeatCount">
        <el-input
          v-model="queryParams.approvedSeatCount"
          placeholder="请输入核定载客数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车架号" prop="vin">
        <el-input
          v-model="queryParams.vin"
          placeholder="请输入车架号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发动机号" prop="engineNo">
        <el-input
          v-model="queryParams.engineNo"
          placeholder="请输入发动机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="燃料种类" prop="gasType">
        <el-select v-model="queryParams.gasType" placeholder="请选择燃料种类" clearable size="small">
          <el-option
            v-for="dict in gasTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="新车购置价" prop="newVehiclePrice">
        <el-input
          v-model="queryParams.newVehiclePrice"
          placeholder="请输入新车购置价"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="厂牌型号" prop="vehicleFactoryName">
        <el-input
          v-model="queryParams.vehicleFactoryName"
          placeholder="请输入厂牌型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行业车型代码" prop="industryVehicleCode">
        <el-input
          v-model="queryParams.industryVehicleCode"
          placeholder="请输入行业车型代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="功率" prop="power">
        <el-input
          v-model="queryParams.power"
          placeholder="请输入功率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整备质量" prop="vehicleQuality">
        <el-input
          v-model="queryParams.vehicleQuality"
          placeholder="请输入整备质量"
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
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['insured:insured:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['insured:insured:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['insured:insured:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['insured:insured:export']"
        >导出</el-button>
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="insuredList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="id" />
      <el-table-column label="保单/投保单主键" align="center" prop="policyId" />
      <el-table-column label="号牌号码" align="center" prop="licenseNo" />
      <el-table-column label="号牌种类" align="center" prop="licenseType" :formatter="licenseTypeFormat" />
      <el-table-column label="车辆种类代码" align="center" prop="vehicleKind" :formatter="vehicleKindFormat" />
      <el-table-column label="车辆使用性质代码" align="center" prop="vehicleUseNature" :formatter="vehicleUseNatureFormat" />
      <el-table-column label="车辆初次登记日期" align="center" prop="vehicleFirstRegDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vehicleFirstRegDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排量" align="center" prop="displacement" />
      <el-table-column label="核定载客数量" align="center" prop="approvedSeatCount" />
      <el-table-column label="车架号" align="center" prop="vin" />
      <el-table-column label="发动机号" align="center" prop="engineNo" />
      <el-table-column label="燃料种类" align="center" prop="gasType" :formatter="gasTypeFormat" />
      <el-table-column label="新车购置价" align="center" prop="newVehiclePrice" />
      <el-table-column label="厂牌型号" align="center" prop="vehicleFactoryName" />
      <el-table-column label="行业车型代码" align="center" prop="industryVehicleCode" />
      <el-table-column label="功率" align="center" prop="power" />
      <el-table-column label="整备质量" align="center" prop="vehicleQuality" />
      <el-table-column label="版本号" align="center" prop="version" />
      <el-table-column label="验车照片地址，用逗号分隔" align="center" prop="imgUrls" />
      <el-table-column label="创建时间" align="center" prop="insertTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insertTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['insured:insured:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['insured:insured:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改被保险车辆信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保单/投保单主键" prop="policyId">
          <el-input v-model="form.policyId" placeholder="请输入保单/投保单主键" />
        </el-form-item>
        <el-form-item label="号牌号码" prop="licenseNo">
          <el-input v-model="form.licenseNo" placeholder="请输入号牌号码" />
        </el-form-item>
        <el-form-item label="号牌种类">
          <el-select v-model="form.licenseType" placeholder="请选择号牌种类">
            <el-option
              v-for="dict in licenseTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆种类代码">
          <el-select v-model="form.vehicleKind" placeholder="请选择车辆种类代码">
            <el-option
              v-for="dict in vehicleKindOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆使用性质代码">
          <el-select v-model="form.vehicleUseNature" placeholder="请选择车辆使用性质代码">
            <el-option
              v-for="dict in vehicleUseNatureOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆初次登记日期" prop="vehicleFirstRegDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.vehicleFirstRegDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择车辆初次登记日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="排量" prop="displacement">
          <el-input v-model="form.displacement" placeholder="请输入排量" />
        </el-form-item>
        <el-form-item label="核定载客数量" prop="approvedSeatCount">
          <el-input v-model="form.approvedSeatCount" placeholder="请输入核定载客数量" />
        </el-form-item>
        <el-form-item label="车架号" prop="vin">
          <el-input v-model="form.vin" placeholder="请输入车架号" />
        </el-form-item>
        <el-form-item label="发动机号" prop="engineNo">
          <el-input v-model="form.engineNo" placeholder="请输入发动机号" />
        </el-form-item>
        <el-form-item label="燃料种类">
          <el-select v-model="form.gasType" placeholder="请选择燃料种类">
            <el-option
              v-for="dict in gasTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="新车购置价" prop="newVehiclePrice">
          <el-input v-model="form.newVehiclePrice" placeholder="请输入新车购置价" />
        </el-form-item>
        <el-form-item label="厂牌型号" prop="vehicleFactoryName">
          <el-input v-model="form.vehicleFactoryName" placeholder="请输入厂牌型号" />
        </el-form-item>
        <el-form-item label="行业车型代码" prop="industryVehicleCode">
          <el-input v-model="form.industryVehicleCode" placeholder="请输入行业车型代码" />
        </el-form-item>
        <el-form-item label="功率" prop="power">
          <el-input v-model="form.power" placeholder="请输入功率" />
        </el-form-item>
        <el-form-item label="整备质量" prop="vehicleQuality">
          <el-input v-model="form.vehicleQuality" placeholder="请输入整备质量" />
        </el-form-item>
        <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="验车照片地址，用逗号分隔" prop="imgUrls">
          <el-input v-model="form.imgUrls" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建时间" prop="insertTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.insertTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInsured, getInsured, delInsured, addInsured, updateInsured } from "@/api/insured/insured";

export default {
  name: "Insured",
  data() {
    return {
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
      // 被保险车辆信息表格数据
      insuredList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 号牌种类字典
      licenseTypeOptions: [],
      // 车辆种类代码字典
      vehicleKindOptions: [],
      // 车辆使用性质代码字典
      vehicleUseNatureOptions: [],
      // 燃料种类字典
      gasTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyId: null,
        licenseNo: null,
        licenseType: null,
        vehicleKind: null,
        vehicleUseNature: null,
        vehicleFirstRegDate: null,
        displacement: null,
        approvedSeatCount: null,
        vin: null,
        engineNo: null,
        gasType: null,
        newVehiclePrice: null,
        vehicleFactoryName: null,
        industryVehicleCode: null,
        power: null,
        vehicleQuality: null,
        version: null,
        imgUrls: null,
        insertTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        policyId: [
          { required: true, message: "保单/投保单主键不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("license_type").then(response => {
      this.licenseTypeOptions = response.data;
    });
    this.getDicts("vehicle_kind").then(response => {
      this.vehicleKindOptions = response.data;
    });
    this.getDicts("vehicle_use_nature").then(response => {
      this.vehicleUseNatureOptions = response.data;
    });
    this.getDicts("vehicle_gas_type").then(response => {
      this.gasTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询被保险车辆信息列表 */
    getList() {
      this.loading = true;
      listInsured(this.queryParams).then(response => {
        this.insuredList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 号牌种类字典翻译
    licenseTypeFormat(row, column) {
      return this.selectDictLabel(this.licenseTypeOptions, row.licenseType);
    },
    // 车辆种类代码字典翻译
    vehicleKindFormat(row, column) {
      return this.selectDictLabel(this.vehicleKindOptions, row.vehicleKind);
    },
    // 车辆使用性质代码字典翻译
    vehicleUseNatureFormat(row, column) {
      return this.selectDictLabel(this.vehicleUseNatureOptions, row.vehicleUseNature);
    },
    // 燃料种类字典翻译
    gasTypeFormat(row, column) {
      return this.selectDictLabel(this.gasTypeOptions, row.gasType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        policyId: null,
        licenseNo: null,
        licenseType: null,
        vehicleKind: null,
        vehicleUseNature: null,
        vehicleFirstRegDate: null,
        displacement: null,
        approvedSeatCount: null,
        vin: null,
        engineNo: null,
        gasType: null,
        newVehiclePrice: null,
        vehicleFactoryName: null,
        industryVehicleCode: null,
        power: null,
        vehicleQuality: null,
        version: null,
        imgUrls: null,
        insertTime: null,
        updateTime: null
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
      this.reset();
      this.open = true;
      this.title = "添加被保险车辆信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInsured(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改被保险车辆信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInsured(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addInsured(this.form).then(response => {
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
      this.$confirm('是否确认删除被保险车辆信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInsured(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('insured/insured/export', {
        ...this.queryParams
      }, `insured_insured.xlsx`)
    }
  }
};
</script>