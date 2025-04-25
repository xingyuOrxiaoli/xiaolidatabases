<template>
  <div class="finance-view">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>导航菜单</span>
            </template>
            <el-menu-item index="1-1">收支统计</el-menu-item>
            <el-menu-item index="1-2">报表</el-menu-item>
            <el-menu-item index="1-3">标签管理</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="18">
        <div v-if="loading">
          <el-skeleton loading effect="wave" />
        </div>
        <div v-else>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-row :gutter="16" class="stats-row">
                <el-col :span="8">
                  <el-card shadow="hover" class="stat-card income-card">
                    <div class="card-content">
                      <el-icon class="card-icon"><Money /></el-icon>
                      <div class="card-text">
                        <div class="card-label">月收入</div>
                        <div class="card-value">¥{{ monthlyIncome }}</div>
                      </div>
                    </div>
                  </el-card>
              </el-col>
                <el-col :span="8">
                  <el-card shadow="hover" class="stat-card expense-card">
                    <div class="card-content">
                      <el-icon class="card-icon"><Wallet /></el-icon>
                      <div class="card-text">
                        <div class="card-label">月支出</div>
                        <div class="card-value">¥{{ monthlyExpense }}</div>
                      </div>
                    </div>
                  </el-card>
              </el-col>
                <el-col :span="8">
                  <el-card shadow="hover" class="stat-card balance-card">
                    <div class="card-content">
                      <el-icon class="card-icon"><Coin /></el-icon>
                      <div class="card-text">
                        <div class="card-label">当前结余</div>
                        <div class="card-value">¥{{ monthlyBalance }}</div>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <el-row :gutter="16" class="quick-actions">
            <el-col :span="6">
              <el-button type="primary" icon="el-icon-circle-plus" class="action-btn">快速记账</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="success" icon="el-icon-download" class="action-btn">导出报表</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="info" icon="el-icon-setting" class="action-btn">分类设置</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="warning" icon="el-icon-data-analysis" class="action-btn">数据看板</el-button>
            </el-col>
          </el-row>
          <el-card header="收支图表" shadow="always" style="margin-top: 20px;">
            <div class="chart-container">
  <el-row :gutter="12">
    <el-col :span="18">
      <line-chart :chart-data="chartData" />
    </el-col>
    <el-col :span="6">
      <div class="chart-metrics">
        <el-statistic title="日均支出" :value="dailyAverageExpense" />
        <el-statistic title="最大支出" :value="maxExpense" />
        <el-statistic title="收入构成" :value="incomeSources" />
      </div>
    </el-col>
  </el-row>
</div>
          </el-card>
          <el-card header="收支记录" shadow="always">
            <el-table :data="records" border stripe hover>
              <el-table-column prop="date" label="日期" sortable />
              <el-table-column prop="amount" label="金额" sortable />
              <el-table-column prop="category" label="分类" />
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="small"
                    @click="editRecord(scope.row)"
                  >编辑</el-button>
                  <el-button
                    type="text"
                    size="small"
                    @click="deleteRecord(scope.row)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <div class="stats-box">
              <el-button @click="exportCSV" type="primary">导出数据</el-button>
            </div>
          </el-card>
          <el-card header="标签管理" shadow="always">
            <el-input v-model="newLabelName" placeholder="新建标签" style="width:200px" />
            <el-button @click="addLabel">添加标签</el-button>
            <el-table :data="labels" border stripe hover>
              <el-table-column prop="labelName" label="标签名称" />
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="small"
                    @click="deleteLabel(scope.row.id)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-message ref="message" />
  </div>
</template>

<script>
export default {
  name: 'FinanceView',
  data() {
    return {
      records: [],
      chartData: {},
      labels: [],
      newLabelName: '',
      loading: true
    }
  },
  computed: {
    monthlyIncome() {
      return this.records
        .filter(r => r.type === '收入')
        .reduce((sum, r) => sum + parseFloat(r.amount), 0)
    },
    monthlyExpense() {
      return this.records
        .filter(r => r.type === '支出')
        .reduce((sum, r) => sum + parseFloat(r.amount), 0)
    },
    monthlyBalance() {
      return this.monthlyIncome - this.monthlyExpense
    }
  },
  methods: {
    exportCSV() {
      const data = this.records
        .map(r => `${r.date},${r.type},${r.amount},${r.category}`)
        .join('\n')
      const blob = new Blob(['日期,类型,金额,分类\n' + data], { type: 'text/csv' })
      const link = document.createElement('a')
      link.href = URL.createObjectURL(blob)
      link.download = '财务统计.csv'
      link.click()
    },
    addLabel() {
      if (this.newLabelName) {
        this.labels.push({ 
          id: Date.now(),
          labelName: this.newLabelName 
        })
        this.newLabelName = ''
        this.$refs.message({
          message: '标签添加成功',
          type: 'success'
        })
      } else {
        this.$refs.message({
          message: '标签名称不能为空',
          type: 'warning'
        })
      }
    },
    deleteLabel(labelId) {
      this.labels = this.labels.filter(label => label.id !== labelId)
      this.$refs.message({
        message: '标签删除成功',
        type: 'success'
      })
    },
    editRecord(record) {
      // 编辑记录的逻辑
    },
    deleteRecord(record) {
      // 删除记录的逻辑
      this.records = this.records.filter(r => r !== record)
      this.$refs.message({
        message: '记录删除成功',
        type: 'success'
      })
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  async mounted() {
    try {
      const response = await fetch('http://localhost:3000/api/life_cust')
      const data = await response.json()
      this.records = data
      this.chartData = {
        labels: data.map(d => d.date),
        datasets: [{
          label: '支出趋势',
          data: data.map(d => d.amount),
          borderColor: '#409EFF'
        }]
      }
      this.loading = false
      this.$refs.message({
        message: '数据加载完成',
        type: 'success'
      })
    } catch (error) {
      console.error('获取生活消费数据失败:', error)
      this.$refs.message({
        message: '获取数据失败，请稍后重试',
        type: 'error'
      })
    }
  }
}
</script>

<style scoped>
.finance-view {
  padding: 20px;
}
.chart-container {
  height: 400px;
  margin: 20px 0;
  background: #fff;
  border-radius: 8px;
  padding: 15px;
}
.stats-box {
  margin: 20px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.stat-card {
  padding: 20px;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.income-card {
  background: linear-gradient(135deg, var(--el-color-primary-light-8), var(--el-color-primary-light-6));
  color: var(--el-color-primary-dark-2);
  border-left: 4px solid var(--el-color-primary);
}

.expense-card {
  background: linear-gradient(135deg, var(--el-color-danger-light-8), var(--el-color-danger-light-6));
  color: var(--el-color-danger-dark-2);
  border-left: 4px solid var(--el-color-danger);
}

.balance-card {
  background: linear-gradient(135deg, var(--el-color-success-light-8), var(--el-color-success-light-6));
  color: var(--el-color-success-dark-2);
  border-left: 4px solid var(--el-color-success);
}

.quick-actions {
  margin: 20px 0;
}

.action-btn {
  border-radius: 12px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100px;
}

.action-btn i {
  font-size: 24px;
  margin-bottom: 8px;
}
</style>