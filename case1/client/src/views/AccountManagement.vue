<template>
  <div class="account-management">
    <div class="top-nav">
      <div class="logo">
        <img src="@/assets/logo.png" alt="Logo" />
        <span>账号管理系统</span>
      </div>
      <div class="nav-items">
        <el-dropdown trigger="click">
          <span class="el-dropdown-link">
            <img src="@/assets/user-avatar.png" alt="Avatar" class="avatar" />
          </span>
          <template #dropdown>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <div class="content-wrapper">
      <el-aside width="200px">
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-submenu index="1">
            <template #title>
              <i class="el-icon-menu"></i>
              <span>个人账号管理</span>
            </template>
            <el-menu-item index="1-1" @click="navigateTo('home')">首页</el-menu-item>
            <el-menu-item index="1-2" @click="navigateTo('account-link')">账号管理</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <div v-if="currentPageName === 'home'" class="top-stats">
          <el-row :gutter="50">
            <el-col :span="6">
              <div class="stat-card">
                <p>总账号数量</p>
                <h2>{{ accountCount }}</h2>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-card">
                <p>VPN账号数量</p>
                <h2>{{ vpnAccountCount }}</h2>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-card">
                <p>手机号账号数量</p>
                <h2>{{ phoneAccountCount }}</h2>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-card">
                <p>注册账号日期统计</p>
                <h2>{{ registrationChartData }}</h2>
              </div>
            </el-col>
          </el-row>
        </div>
        <div v-else-if="currentPageName === 'account-link'">
          <!-- 账号关联页面内容 -->
          <div class="search-form">
            <el-input 
              v-model="searchKeyword" 
              placeholder="请输入平台名称" 
              style="width: 300px"
              @keyup.enter="fetchAccounts()"
            >
              <template #append>
                <el-button type="primary" icon="el-icon-search" @click="fetchAccounts()">查询</el-button>
              </template>
            </el-input>
            <el-button type="primary" icon="el-icon-plus" @click="handleAdd" style="margin-left: 10px;">添加</el-button>
          </div>
          <el-table :data="accounts" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="name" label="平台名称" width="150"></el-table-column>
            <el-table-column prop="username" label="账号"></el-table-column>
            <el-table-column prop="credential" label="密码">
              <template #default="scope">
                {{ scope.row.showPassword ? scope.row.credential : '******' }}
                <el-button @click="togglePasswordVisibility(scope.row)" size="mini">
                  {{ scope.row.showPassword ? '隐藏' : '显示' }}
                </el-button>
              </template>
            </el-table-column>
            <el-table-column prop="url" label="网站URL" :formatter="formatUrl"></el-table-column>
            <el-table-column prop="has_web" label="是否有web" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.has_web ? 'success' : 'danger'">
                  {{ scope.row.has_web ? '有' : '无' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="has_app" label="是否有app" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.has_app ? 'success' : 'danger'">
                  {{ scope.row.has_app ? '有' : '无' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="need_vpn" label="需VPN" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.need_vpn ? 'warning' : 'info'">
                  {{ scope.row.need_vpn ? '需要' : '无需' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="is_phone" label="手机号" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.is_phone ? 'success' : 'danger'">
                  {{ scope.row.is_phone ? '是' : '否' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="180" align="center">
              <template #default="scope">
                <div class="action-buttons">
                  <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" size="mini"></el-button>
                  <el-button type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" size="mini"></el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
          ></el-pagination>
        </div>
      </el-main>
    </div>
    <el-dialog
      :visible.sync="dialogVisible"
      title="添加账号"
      width="30%"
      @close="handleCloseDialog"
    >
      <el-form :model="currentAccount" ref="accountForm" :rules="rules">
        <el-form-item label="平台名称" prop="name">
          <el-input v-model="currentAccount.name"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="username">
          <el-input v-model="currentAccount.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="credential">
          <el-input v-model="currentAccount.credential"></el-input>
        </el-form-item>
        <el-form-item label="网站URL" prop="url">
          <el-input v-model="currentAccount.url"></el-input>
        </el-form-item>
        <el-form-item label="是否有web" prop="has_web">
          <el-switch v-model="currentAccount.has_web"></el-switch>
        </el-form-item>
        <el-form-item label="是否有app" prop="has_app">
          <el-switch v-model="currentAccount.has_app"></el-switch>
        </el-form-item>
        <el-form-item label="需VPN" prop="need_vpn">
          <el-switch v-model="currentAccount.need_vpn"></el-switch>
        </el-form-item>
        <el-form-item label="手机号" prop="is_phone">
          <el-switch v-model="currentAccount.is_phone"></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="saveAccount">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import userManager from '../api/userManager.js'
import { ElMessageBox, ElMessage } from 'element-plus'

export default {
  name: 'AccountManagement',
  data() {
    return {
      accounts: [],
      dialogVisible: false,
      currentAccount: {
        name: '',
        username: '',
        credential: '',
        url: '',
        has_web: false,
        has_app: false,
        need_vpn: false,
        is_phone: false
      },
      accountCount: 0,
      vpnAccountCount: 0,
      phoneAccountCount: 0,
      registrationChartData: {},
      currentPageName: 'home',
      pageSize: 10,
      total: 0,
      currentPageNum: 1,
      searchKeyword: '',
      rules: {
        name: [
          { required: true, message: '请输入平台名称', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        credential: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        url: [
          { type: 'url', message: '请输入有效的URL', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    formatUrl(row) {
      return row.url || '暂时没有录入网址';
    },
    navigateTo(page) {
      this.currentPageName = page
    },
    handleEdit(account) {
      this.currentAccount = Object.assign({}, account)
      this.dialogVisible = true
    },
    handleAdd() {
      this.currentAccount = {
        name: '',
        username: '',
        credential: '',
        url: '',
        has_web: false,
        has_app: false,
        need_vpn: false,
        is_phone: false
      }
      this.dialogVisible = true
    },
    async handleDelete(account) {
      try {
        const confirmResult = await ElMessageBox.confirm('确定要删除该账号吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        });
        if (confirmResult === 'confirm') {
          await userManager.deleteAccount(account.id);
          this.fetchAccounts();
        }
      } catch (error) {
        console.error('删除账号失败:', error);
      }
    },
    async saveAccount() {
      this.$refs['accountForm'].validate(async valid => {
        if (valid) {
          try {
            const accountData = {
              name: this.currentAccount.name,
              username: this.currentAccount.username,
              credential: this.currentAccount.credential,
              url: this.currentAccount.url,
              has_web: this.currentAccount.has_web,
              need_vpn: this.currentAccount.need_vpn,
              has_app: this.currentAccount.has_app,
              is_phone: this.currentAccount.is_phone
            }
            if (this.currentAccount.id) {
              await userManager.updateAccount(this.currentAccount.id, accountData)
              ElMessage.success('修改成功')
            } else {
              await userManager.addAccount(accountData)
              ElMessage.success('添加成功')
            }
            this.fetchAccounts()
            this.dialogVisible = false
          } catch (error) {
            console.error('保存账号失败:', error)
            ElMessage.error('保存失败，请稍后重试')
          }
        }
      })
    },
    togglePasswordVisibility(account) {
      account.showPassword = !account.showPassword
    },
    async fetchAccountStats() {
      try {
        const stats = await userManager.getAccountStats()
        this.accountCount = stats.accountCount[0].count
        this.vpnAccountCount = stats.vpnAccountCount[0].count
        this.phoneAccountCount = stats.phoneAccountCount[0].count
        this.registrationChartData = stats.registrationChartData
      } catch (error) {
        console.error('获取统计数据失败:', error)
      }
    },
    async fetchAccounts(page = this.currentPageNum, pageSize = this.pageSize) {
      try {
        const queryParams = { keyword: this.searchKeyword }
        const data = await userManager.getAccounts({ page, pageSize, ...queryParams })
        this.accounts = data.data
        this.total = data.total
      } catch (error) {
        console.error('获取账号数据失败:', error)
      }
    },
    logout() {
      // 处理退出登录逻辑
    },
    handleSizeChange(size) {
      this.pageSize = size
      this.fetchAccounts(1, size)
    },
    handleCurrentChange(currentPageNum) {
      this.currentPageNum = currentPageNum
      this.fetchAccounts(currentPageNum, this.pageSize)
    },
    handleCloseDialog() {
      this.dialogVisible = false
    }
  },
  async mounted() {
    this.fetchAccounts()
    this.fetchAccountStats()
  }
}
</script>

<style scoped>
.account-management {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background: linear-gradient(to right, #409eff, #66b1ff);
  color: white;
}
.logo {
  display: flex;
  align-items: center;
}
.logo img {
  height: 30px;
  margin-right: 10px;
}
.nav-items {
  display: flex;
  align-items: center;
}
.avatar {
  height: 30px;
  width: 30px;
  border-radius: 50%;
  margin-left: 20px;
}
.content-wrapper {
  display: flex;
  flex: 1;
}
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  flex: 1;
  padding: 20px;
}
.top-stats {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}
.stat-card {
  text-align: center;
  padding: 20px;
  background: linear-gradient(to bottom right, #f0f9eb, #c2e7b0);
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.search-form {
  margin-bottom: 20px;
}
.action-buttons {
  display: flex;
  justify-content: space-around;
}
.el-button {
  transition: transform 0.3s ease;
}
.el-button:hover {
  transform: scale(1.1);
}
</style>