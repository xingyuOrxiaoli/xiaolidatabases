import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/finance',
    name: 'Finance',
    component: () => import('@/views/FinanceView.vue')
  },
  {
    path: '/account',
    name: 'Account',
    component: () => import('@/views/AccountManagement.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/HomeView.vue')
  },
  {
    path: '/income-expense',
    name: 'IncomeExpense',
    component: () => import('@/views/IncomeExpenseView.vue')
  },
  {
    path: '/info-management',
    name: 'InfoManagement',
    component: () => import('@/views/InfoManagementView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router