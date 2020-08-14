<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保单/投保单表的主键" prop="policyId">
        <el-input
          v-model="queryParams.policyId"
          placeholder="请输入保单/投保单表的主键"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件号" prop="idNo">
        <el-input
          v-model="queryParams.idNo"
          placeholder="请输入证件号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件类型代码" prop="idType">
        <el-select v-model="queryParams.idType" placeholder="请选择证件类型代码" clearable size="small">
          <el-option
            v-for="dict in idTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户角色" prop="userRoleType">
        <el-select v-model="queryParams.userRoleType" placeholder="请选择客户角色" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="电话" prop="cellphone">
        <el-input
          v-model="queryParams.cellphone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户类型：1-个人 2-企业" prop="customerType">
        <el-select v-model="queryParams.customerType" placeholder="请选择客户类型：1-个人 2-企业" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱地址" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱地址"
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
      <el-form-item label="性别1-男，2-女" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别1-男，2-女"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="1-投保人同车主 , 2-被保人投保人" prop="relation">
        <el-input
          v-model="queryParams.relation"
          placeholder="请输入1-投保人同车主 , 2-被保人投保人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['customer:customer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['customer:customer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['customer:customer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:customer:export']"
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

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="id" />
      <el-table-column label="保单/投保单表的主键" align="center" prop="policyId" />
      <el-table-column label="证件号" align="center" prop="idNo" />
      <el-table-column label="证件类型代码" align="center" prop="idType" :formatter="idTypeFormat" />
      <el-table-column label="客户名称" align="center" prop="userName" />
      <el-table-column label="客户角色" align="center" prop="userRoleType" />
      <el-table-column label="电话" align="center" prop="cellphone" />
      <el-table-column label="联系地址" align="center" prop="address" />
      <el-table-column label="客户类型：1-个人 2-企业" align="center" prop="customerType" />
      <el-table-column label="邮箱地址" align="center" prop="email" />
      <el-table-column label="版本号" align="center" prop="version" />
      <el-table-column label="创建时间" align="center" prop="insertTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insertTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别1-男，2-女" align="center" prop="gender" />
      <el-table-column label="1-投保人同车主 , 2-被保人投保人" align="center" prop="relation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:customer:remove']"
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

    <!-- 添加或修改投保单客户信息（投保人，被保人，车主）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保单/投保单表的主键" prop="policyId">
          <el-input v-model="form.policyId" placeholder="请输入保单/投保单表的主键" />
        </el-form-item>
        <el-form-item label="证件号" prop="idNo">
          <el-input v-model="form.idNo" placeholder="请输入证件号" />
        </el-form-item>
        <el-form-item label="证件类型代码">
          <el-select v-model="form.idType" placeholder="请选择证件类型代码">
            <el-option
              v-for="dict in idTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="客户角色">
          <el-select v-model="form.userRoleType" placeholder="请选择客户角色">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="电话" prop="cellphone">
          <el-input v-model="form.cellphone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="客户类型：1-个人 2-企业">
          <el-select v-model="form.customerType" placeholder="请选择客户类型：1-个人 2-企业">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱地址" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱地址" />
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
        <el-form-item label="性别1-男，2-女" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别1-男，2-女" />
        </el-form-item>
        <el-form-item label="1-投保人同车主 , 2-被保人投保人" prop="relation">
          <el-input v-model="form.relation" placeholder="请输入1-投保人同车主 , 2-被保人投保人" />
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
import { listCustomer, getCustomer, delCustomer, addCustomer, updateCustomer } from "@/api/customer/customer";

export default {
  name: "Customer",
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
      // 投保单客户信息（投保人，被保人，车主）表格数据
      customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 证件类型代码字典
      idTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyId: null,
        idNo: null,
        idType: null,
        userName: null,
        userRoleType: null,
        cellphone: null,
        address: null,
        customerType: null,
        email: null,
        version: null,
        insertTime: null,
        gender: null,
        relation: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        policyId: [
          { required: true, message: "保单/投保单表的主键不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("id_type").then(response => {
      this.idTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询投保单客户信息（投保人，被保人，车主）列表 */
    getList() {
      this.loading = true;
      listCustomer(this.queryParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 证件类型代码字典翻译
    idTypeFormat(row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
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
        idNo: null,
        idType: null,
        userName: null,
        userRoleType: null,
        cellphone: null,
        address: null,
        customerType: null,
        email: null,
        version: null,
        insertTime: null,
        updateTime: null,
        gender: null,
        relation: null
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
      this.title = "添加投保单客户信息（投保人，被保人，车主）";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投保单客户信息（投保人，被保人，车主）";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCustomer(this.form).then(response => {
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
      this.$confirm('是否确认删除投保单客户信息（投保人，被保人，车主）编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCustomer(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/customer/export', {
        ...this.queryParams
      }, `customer_customer.xlsx`)
    }
  }
};
</script>