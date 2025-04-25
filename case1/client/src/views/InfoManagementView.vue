<template>
  <div class="info-management">
    <h2>账号信息管理</h2>
    <div class="stats-section">
      <h3>账号分类统计</h3>
      <div class="chart-container">
        <!-- 这里将添加分类统计图表 -->
      </div>
    </div>
    <div class="table-section">
      <h3>账号列表</h3>
      <el-form :inline="true" :model="queryParams" class="demo-form-inline">
        <el-form-item label="平台类型">
          <el-select v-model="queryParams.category" placeholder="请选择平台类型">
            <el-option v-for="category in categories" :key="category" :label="category" :value="category"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchAccounts">查询</el-button>
        </el-form-item>
      </el-form>
      <el-input v-model="searchKeyword" placeholder="请输入搜索关键字" @keyup.enter="fetchAccounts" />
      <el-table :data="filteredAccounts" style="width: 100%">
        <el-table-column prop="id" label="账号ID"></el-table-column>
        <el-table-column prop="name" label="平台名称"></el-table-column>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="password" label="密码">
          <template slot-scope="scope">
            <div class="password-input">
              <el-input
                v-model="scope.row.password"
                type="password"
                :readonly="true"
                :show-password="scope.row.showPassword"
                :suffix-icon="scope.row.showPassword ? 'el-icon-eye' : 'el-icon-eye-open'"
                @click="togglePasswordVisibility(scope.row)"
              ></el-input>
              <el-button
                type="text"
                size="mini"
                @click="copyPasswordToClipboard(scope.row.password)"
                icon="el-icon-copy-document"
                class="copy-password"
              ></el-button>
              <div class="password-strength-bar" :style="{ width: scope.row.strengthLevel + '%', background: getPasswordStrengthColor(scope.row.strengthLevel) }"></div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="url" label="网站URL"></el-table-column>
        <el-table-column prop="isNeedVPN" label="是否需要VPN">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.isNeedVPN" disabled></el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="isPhone" label="是否是手机号">
          <template slot-scope="scope">
            <el-icon v-if="scope.row.isPhone" name="el-icon-mobile"></el-icon>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="editAccount(scope.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="deleteAccount(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
      <el-button type="primary" @click="addAccount()">添加账号</el-button>
    </div>
  </div>
</template>

<script>
import { getAccounts } from '@/api/account'
import { ElMessage } from 'element-plus'

export default {
  name: 'InfoManagementView',
  data() {
    return {
      accounts: [],
      categories: [],
      queryParams: {
        category: '',
        page: 1,
        pageSize: 5
      },
      currentPage: 1,
      total: 0,
      searchKeyword: ''
    }
  },
  computed: {
    filteredAccounts() {
      return this.accounts.filter(account => {
        return account.name.includes(this.searchKeyword) || account.username.includes(this.searchKeyword)
      })
    }
  },
  methods: {
    // 添加分类统计方法
    calculateCategoryStats() {
      // 实现账号分类统计逻辑
    },
    // 获取账号数据的方法
    async fetchAccounts() {
      try {
        const response = await getAccounts({
          ...this.queryParams,
          keyword: this.searchKeyword,
          page: this.currentPage,
          pageSize: this.pageSize
        })
        this.accounts = response.data.map(account => ({
          ...account,
          showPassword: false,
          strengthLevel: this.calculatePasswordStrength(account.password)
        }))
        this.total = response.total
      } catch (error) {
        console.error('获取账号数据失败:', error)
      }
    },
    // 切换密码可见性方法
    togglePasswordVisibility(row) {
      row.showPassword = !row.showPassword
    },
    // 复制密码到剪贴板方法
    copyPasswordToClipboard(password) {
      navigator.clipboard.writeText(password).then(() => {
        ElMessage.success('密码已复制到剪贴板')
      }).catch(err => {
        ElMessage.error('复制密码失败:', err)
      })
    },
    // 编辑账号方法
    editAccount(row) {
      // 实现编辑账号逻辑
    },
    // 删除账号方法
    deleteAccount(row) {
      // 实现删除账号逻辑
    },
    // 添加账号方法
    addAccount() {
      // 实现添加账号逻辑
    },
    // 计算密码强度方法
    calculatePasswordStrength(password) {
      let strengthLevel = 0
      // 密码强度计算逻辑，根据复杂度返回0-100的数值
      if (password.length >= 8 && /[A-Z]/.test(password) && /[a-z]/.test(password) && /[0-9]/.test(password) && /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(password)) {
        strengthLevel = 100
      } else if (password.length >= 6 && /[A-Z]/.test(password) && /[a-z]/.test(password)) {
        strengthLevel = 60
      } else {
        strengthLevel = 30
      }
      return strengthLevel
    },
    // 获取密码强度颜色方法
    getPasswordStrengthColor(strengthLevel) {
      if (strengthLevel >= 80) {
        return 'green'
      } else if (strengthLevel >= 40) {
        return 'yellow'
      } else {
        return 'red'
      }
    },
    handleSizeChange(size) {
      this.pageSize = size
      this.fetchAccounts()
    },
    handleCurrentChange(page) {
      this.currentPage = page
      this.fetchAccounts()
    }
  },
  mounted() {
    // 加载账号数据
    this.fetchAccounts()
    this.calculateCategoryStats()
  }
}
</script>

<style scoped>
.info-management {
  padding: 20px;
}
.stats-section {
  margin-top: 20px;
}
.chart-container {
  height: 300px;
  background: #f5f5f5;
  border-radius: 4px;
  padding: 10px;
}
.table-section {
  margin-top: 20px;
}
.demo-form-inline {
  margin-bottom: 10px;
}
.password-input {
  display: flex;
  align-items: center;
}
.copy-password {
  margin-left: 5px;
}
.password-strength-bar {
  height: 10px;
  border-radius: 5px;
  margin-left: 5px;
}
</style>