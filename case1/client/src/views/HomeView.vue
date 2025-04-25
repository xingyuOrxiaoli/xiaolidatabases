<template>
  <div class="home">
    <h1>欢迎来到首页</h1>
    <div class="menu-container">
      <div class="card" @click="goToFinance">
        <h2>账单管理</h2>
        <p>管理你的财务收支</p>
      </div>
      <div class="card" @click="goToAccount">
        <h2>账号管理</h2>
        <p>管理你的各个平台账号</p>
      </div>
      <div class="card" @click="goToDocument">
        <h2>文档管理</h2>
        <p>管理你的各类文档</p>
      </div>
    </div>
    <div class="summary-panel">
      <h2>统计概览</h2>
      <div class="stats-cards">
        <el-row :gutter="20">
          <el-col :span="24" :xs="24" :sm="12" :md="6" v-for="stat in stats" :key="stat.id">
            <el-card :style="statStyles(stat.id)" class="stat-card">
              <div slot="header">
                <i :class="statIcon(stat.id)" class="el-icon"></i>
                <span>{{ stat.title }}</span>
              </div>
              <div class="stat-value">{{ stat.value }}</div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div class="quick-actions">
        <el-button v-for="action in quickActions" :key="action.id" :to="action.route" type="primary" icon="el-icon-document">
          <span>{{ action.label }}</span>
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { ElRow, ElCol, ElButton, ElCard } from 'element-plus'

export default {
  name: 'HomeView',
  components: {
    ElRow,
    ElCol,
    ElButton,
    ElCard
  },
  setup() {
    const router = useRouter()

    const goToFinance = () => {
      router.push('/finance')
    }

    const goToAccount = () => {
      router.push('/account')
    }

    const goToDocument = () => {
      router.push('/document')
    }

    const stats = [
      { id: 1, title: '总收入', value: '$10,000' },
      { id: 2, title: '总支出', value: '$5,000' },
      { id: 3, title: '总文档数', value: '100' },
      { id: 4, title: '总账号数', value: '5' }
    ]

    const quickActions = [
      { id: 1, label: '账单', route: '/finance' },
      { id: 2, label: '账号', route: '/account' },
      { id: 3, label: '文档', route: '/document' }
    ]

    const statStyles = (id) => {
      const colors = ['#FFA07A', '#87CEFA', '#98FB98', '#FFD700']
      return {
        background: `linear-gradient(135deg, ${colors[id - 1]}, ${colors[id % colors.length]})`
      }
    }

    const statIcon = (id) => {
      const icons = ['el-icon-money', 'el-icon-wallet', 'el-icon-document', 'el-icon-user']
      return icons[id - 1]
    }

    return {
      goToFinance,
      goToAccount,
      goToDocument,
      stats,
      quickActions,
      statStyles,
      statIcon
    }
  }
}
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
  background: linear-gradient(135deg, #f0f0f0, #e0e0e0);
  min-height: 100vh;
  padding: 20px;
}

.menu-container {
  display: flex;
  justify-content: space-around;
  margin-top: 30px;
}

.card {
  width: 200px;
  height: 150px;
  background-color: #fff;
  border-radius: 16px;
  padding: 20px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.card:hover {
  background-color: #e0e0e0;
  transform: translateY(-5px);
}

.summary-panel {
  margin-top: 30px;
  padding: 20px;
  background-color: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.stats-cards {
  margin-top: 20px;
}

.stat-card {
  border-radius: 16px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
}

.quick-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
}
</style>