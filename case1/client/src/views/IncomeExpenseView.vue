<template>
  <div class="income-expense">
    <h1>收支记录</h1>
    <div class="summary-cards">
      <el-card class="summary-card">
        <div class="card-title">总支出</div>
        <div class="card-value">¥{{ stats.totalExpense }}</div>
      </el-card>
      <el-card class="summary-card">
        <div class="card-title">总收入</div>
        <div class="card-value">¥{{ stats.totalIncome }}</div>
      </el-card>
      <el-card class="summary-card">
        <div class="card-title">账户余额</div>
        <div class="card-value">¥{{ stats.balance }}</div>
      </el-card>
    </div>
    <div class="report-section">
      <h3>收支统计报表</h3>
      <div class="chart-container">
        <el-table :data="records" style="width: 100%">
          <el-table-column prop="date" label="日期" width="180" />
          <el-table-column prop="type" label="类型" width="180" />
          <el-table-column prop="amount" label="金额" />
          <el-table-column prop="category" label="分类" />
          <el-table-column prop="description" label="描述" />
        </el-table>
        <el-button type="primary" @click="exportData" style="margin-top: 20px">导出数据</el-button>
      </div>
    </div>
    <div class="label-section">
      <h3>标签管理</h3>
      <div>
        <el-input v-model="newLabelName" placeholder="输入新标签名称" />
        <el-button type="primary" @click="addLabel">添加标签</el-button>
      </div>
      <el-table :data="labels" style="width: 100%">
        <el-table-column prop="labelName" label="标签名称" />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="danger" @click="deleteLabel(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IncomeExpenseView',
  data() {
    return {
      records: [
        {
          date: '2023-01-01',
          type: '收入',
          amount: 5000,
          category: '工资',
          description: '一月工资'
        },
        {
          date: '2023-01-02',
          type: '支出',
          amount: 2000,
          category: '房租',
          description: '一月房租'
        }
      ],
      labels: [],
      selectedLabels: [],
      newLabelName: '',
      stats: {}
    }
  },
  methods: {
    calculateStats() {
      let totalIncome = 0
      let totalExpense = 0
      this.records.forEach(record => {
        if (record.type === '收入') {
          totalIncome += record.amount
        } else {
          totalExpense += record.amount
        }
      })
      this.stats = {
        totalIncome,
        totalExpense,
        balance: totalIncome - totalExpense
      }
    },
    exportData() {
      const data = this.records
        .map(r => `${r.date},${r.type},${r.amount},${r.category},${r.description}`)
        .join('\n')
      const blob = new Blob(['日期,类型,金额,分类,描述\n' + data], { type: 'text/csv' })
      const link = document.createElement('a')
      link.href = URL.createObjectURL(blob)
      link.download = '收支记录.csv'
      link.click()
    },
    addLabel() {
      if (this.newLabelName) {
        this.labels.push({ labelName: this.newLabelName })
        this.newLabelName = ''
      }
    },
    deleteLabel(labelId) {
      this.labels = this.labels.filter(label => label.id !== labelId)
    }
  },
  async mounted() {
    try {
      const response = await fetch('http://localhost:3000/api/finance')
      this.records = await response.json()
      this.calculateStats()
    } catch (error) {
      console.error('获取收支数据失败:', error)
    }
  }
}
</script>

<style scoped>
.income-expense {
  padding: 20px;
}
.report-section {
  margin-top: 20px;
}
.chart-container {
  height: 300px;
  background: #f5f5f5;
  border-radius: 4px;
  padding: 10px;
}
.label-section {
  margin-top: 20px;
}
</style>