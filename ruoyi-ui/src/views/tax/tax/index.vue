<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保单/投保单表主键" prop="policyId">
        <el-input
          v-model="queryParams.policyId"
          placeholder="请输入保单/投保单表主键"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴税人识别号" prop="taxPayerNo">
        <el-input
          v-model="queryParams.taxPayerNo"
          placeholder="请输入缴税人识别号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴税人名称" prop="taxPayerName">
        <el-input
          v-model="queryParams.taxPayerName"
          placeholder="请输入缴税人名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车船税标准税款" prop="stdTax">
        <el-input
          v-model="queryParams.stdTax"
          placeholder="请输入车船税标准税款"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当年应缴纳税款" prop="currentTax">
        <el-input
          v-model="queryParams.currentTax"
          placeholder="请输入当年应缴纳税款"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="往年欠缴税款" prop="previousTax">
        <el-input
          v-model="queryParams.previousTax"
          placeholder="请输入往年欠缴税款"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="滞纳金" prop="overdueFee">
        <el-input
          v-model="queryParams.overdueFee"
          placeholder="请输入滞纳金"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应缴纳总金额" prop="totalTaxAmount">
        <el-input
          v-model="queryParams.totalTaxAmount"
          placeholder="请输入应缴纳总金额"
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
          v-hasPermi="['tax:tax:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['tax:tax:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['tax:tax:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tax:tax:export']"
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

    <el-table v-loading="loading" :data="taxList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="id" />
      <el-table-column label="保单/投保单表主键" align="center" prop="policyId" />
      <el-table-column label="缴税人识别号" align="center" prop="taxPayerNo" />
      <el-table-column label="缴税人名称" align="center" prop="taxPayerName" />
      <el-table-column label="车船税标准税款" align="center" prop="stdTax" />
      <el-table-column label="当年应缴纳税款" align="center" prop="currentTax" />
      <el-table-column label="往年欠缴税款" align="center" prop="previousTax" />
      <el-table-column label="滞纳金" align="center" prop="overdueFee" />
      <el-table-column label="联系地址" align="center" prop="address" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="应缴纳总金额" align="center" prop="totalTaxAmount" />
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
            v-hasPermi="['tax:tax:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tax:tax:remove']"
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

    <!-- 添加或修改车船税信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保单/投保单表主键" prop="policyId">
          <el-input v-model="form.policyId" placeholder="请输入保单/投保单表主键" />
        </el-form-item>
        <el-form-item label="缴税人识别号" prop="taxPayerNo">
          <el-input v-model="form.taxPayerNo" placeholder="请输入缴税人识别号" />
        </el-form-item>
        <el-form-item label="缴税人名称" prop="taxPayerName">
          <el-input v-model="form.taxPayerName" placeholder="请输入缴税人名称" />
        </el-form-item>
        <el-form-item label="车船税标准税款" prop="stdTax">
          <el-input v-model="form.stdTax" placeholder="请输入车船税标准税款" />
        </el-form-item>
        <el-form-item label="当年应缴纳税款" prop="currentTax">
          <el-input v-model="form.currentTax" placeholder="请输入当年应缴纳税款" />
        </el-form-item>
        <el-form-item label="往年欠缴税款" prop="previousTax">
          <el-input v-model="form.previousTax" placeholder="请输入往年欠缴税款" />
        </el-form-item>
        <el-form-item label="滞纳金" prop="overdueFee">
          <el-input v-model="form.overdueFee" placeholder="请输入滞纳金" />
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="应缴纳总金额" prop="totalTaxAmount">
          <el-input v-model="form.totalTaxAmount" placeholder="请输入应缴纳总金额" />
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
import { listTax, getTax, delTax, addTax, updateTax } from "@/api/tax/tax";

export default {
  name: "Tax",
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
      // 车船税信息表格数据
      taxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyId: null,
        taxPayerNo: null,
        taxPayerName: null,
        stdTax: null,
        currentTax: null,
        previousTax: null,
        overdueFee: null,
        address: null,
        tel: null,
        totalTaxAmount: null,
        version: null,
        insertTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        policyId: [
          { required: true, message: "保单/投保单表主键不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车船税信息列表 */
    getList() {
      this.loading = true;
      listTax(this.queryParams).then(response => {
        this.taxList = response.rows;
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
        taxPayerNo: null,
        taxPayerName: null,
        stdTax: null,
        currentTax: null,
        previousTax: null,
        overdueFee: null,
        address: null,
        tel: null,
        totalTaxAmount: null,
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
      this.title = "添加车船税信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTax(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车船税信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTax(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addTax(this.form).then(response => {
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
      this.$confirm('是否确认删除车船税信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTax(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tax/tax/export', {
        ...this.queryParams
      }, `tax_tax.xlsx`)
    }
  }
};
</script>