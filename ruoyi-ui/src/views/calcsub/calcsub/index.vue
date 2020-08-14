<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保单/投保单id" prop="policyId">
        <el-input
          v-model="queryParams.policyId"
          placeholder="请输入保单/投保单id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行业平台返回的投保查询码" prop="proposalQueryNo">
        <el-input
          v-model="queryParams.proposalQueryNo"
          placeholder="请输入行业平台返回的投保查询码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行业平台返回的投保预确认码" prop="proposalPreConfirmNo">
        <el-input
          v-model="queryParams.proposalPreConfirmNo"
          placeholder="请输入行业平台返回的投保预确认码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行业平台返回的投保确认码" prop="proposalConfirmNo">
        <el-input
          v-model="queryParams.proposalConfirmNo"
          placeholder="请输入行业平台返回的投保确认码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投保查询码失效日期" prop="proposalQueryNoExpireDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.proposalQueryNoExpireDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择投保查询码失效日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="投保预确认码失效日期" prop="proposalPreConfirmNoExpireDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.proposalPreConfirmNoExpireDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择投保预确认码失效日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="平台返回的无赔款优待系数" prop="ncdRate">
        <el-input
          v-model="queryParams.ncdRate"
          placeholder="请输入平台返回的无赔款优待系数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="平台返回的交通违法系数" prop="trafficViolationRate">
        <el-input
          v-model="queryParams.trafficViolationRate"
          placeholder="请输入平台返回的交通违法系数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保险公司内部的自主核保系数" prop="uwRate">
        <el-input
          v-model="queryParams.uwRate"
          placeholder="请输入保险公司内部的自主核保系数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监管平台返回的基准纯风险保费" prop="platformBenchRiskPrem">
        <el-input
          v-model="queryParams.platformBenchRiskPrem"
          placeholder="请输入监管平台返回的基准纯风险保费"
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
          v-hasPermi="['calcsub:calcsub:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['calcsub:calcsub:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['calcsub:calcsub:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['calcsub:calcsub:export']"
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

    <el-table v-loading="loading" :data="calcsubList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="id" />
      <el-table-column label="保单/投保单id" align="center" prop="policyId" />
      <el-table-column label="行业平台返回的投保查询码" align="center" prop="proposalQueryNo" />
      <el-table-column label="行业平台返回的投保预确认码" align="center" prop="proposalPreConfirmNo" />
      <el-table-column label="行业平台返回的投保确认码" align="center" prop="proposalConfirmNo" />
      <el-table-column label="投保查询码失效日期" align="center" prop="proposalQueryNoExpireDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proposalQueryNoExpireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投保预确认码失效日期" align="center" prop="proposalPreConfirmNoExpireDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proposalPreConfirmNoExpireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="平台返回的无赔款优待系数" align="center" prop="ncdRate" />
      <el-table-column label="平台返回的交通违法系数" align="center" prop="trafficViolationRate" />
      <el-table-column label="保险公司内部的自主核保系数" align="center" prop="uwRate" />
      <el-table-column label="监管平台返回的基准纯风险保费" align="center" prop="platformBenchRiskPrem" />
      <el-table-column label="版本号" align="center" prop="version" />
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
            v-hasPermi="['calcsub:calcsub:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['calcsub:calcsub:remove']"
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

    <!-- 添加或修改保费计算附加信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保单/投保单id" prop="policyId">
          <el-input v-model="form.policyId" placeholder="请输入保单/投保单id" />
        </el-form-item>
        <el-form-item label="行业平台返回的投保查询码" prop="proposalQueryNo">
          <el-input v-model="form.proposalQueryNo" placeholder="请输入行业平台返回的投保查询码" />
        </el-form-item>
        <el-form-item label="行业平台返回的投保预确认码" prop="proposalPreConfirmNo">
          <el-input v-model="form.proposalPreConfirmNo" placeholder="请输入行业平台返回的投保预确认码" />
        </el-form-item>
        <el-form-item label="行业平台返回的投保确认码" prop="proposalConfirmNo">
          <el-input v-model="form.proposalConfirmNo" placeholder="请输入行业平台返回的投保确认码" />
        </el-form-item>
        <el-form-item label="投保查询码失效日期" prop="proposalQueryNoExpireDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.proposalQueryNoExpireDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择投保查询码失效日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投保预确认码失效日期" prop="proposalPreConfirmNoExpireDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.proposalPreConfirmNoExpireDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择投保预确认码失效日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="平台返回的无赔款优待系数" prop="ncdRate">
          <el-input v-model="form.ncdRate" placeholder="请输入平台返回的无赔款优待系数" />
        </el-form-item>
        <el-form-item label="平台返回的交通违法系数" prop="trafficViolationRate">
          <el-input v-model="form.trafficViolationRate" placeholder="请输入平台返回的交通违法系数" />
        </el-form-item>
        <el-form-item label="保险公司内部的自主核保系数" prop="uwRate">
          <el-input v-model="form.uwRate" placeholder="请输入保险公司内部的自主核保系数" />
        </el-form-item>
        <el-form-item label="监管平台返回的基准纯风险保费" prop="platformBenchRiskPrem">
          <el-input v-model="form.platformBenchRiskPrem" placeholder="请输入监管平台返回的基准纯风险保费" />
        </el-form-item>
        <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
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
import { listCalcsub, getCalcsub, delCalcsub, addCalcsub, updateCalcsub } from "@/api/calcsub/calcsub";

export default {
  name: "Calcsub",
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
      // 保费计算附加信息表格数据
      calcsubList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyId: null,
        proposalQueryNo: null,
        proposalPreConfirmNo: null,
        proposalConfirmNo: null,
        proposalQueryNoExpireDate: null,
        proposalPreConfirmNoExpireDate: null,
        ncdRate: null,
        trafficViolationRate: null,
        uwRate: null,
        platformBenchRiskPrem: null,
        version: null,
        insertTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        policyId: [
          { required: true, message: "保单/投保单id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询保费计算附加信息列表 */
    getList() {
      this.loading = true;
      listCalcsub(this.queryParams).then(response => {
        this.calcsubList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        proposalQueryNo: null,
        proposalPreConfirmNo: null,
        proposalConfirmNo: null,
        proposalQueryNoExpireDate: null,
        proposalPreConfirmNoExpireDate: null,
        ncdRate: null,
        trafficViolationRate: null,
        uwRate: null,
        platformBenchRiskPrem: null,
        version: null,
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
      this.title = "添加保费计算附加信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCalcsub(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改保费计算附加信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCalcsub(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCalcsub(this.form).then(response => {
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
      this.$confirm('是否确认删除保费计算附加信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCalcsub(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('calcsub/calcsub/export', {
        ...this.queryParams
      }, `calcsub_calcsub.xlsx`)
    }
  }
};
</script>