import { defineStore } from 'pinia';

// 创建一个store
export const useMainStore = defineStore('main', {
  state: () => ({
    // 这里可以定义状态
    count: 0,
  }),
  actions: {
    // 这里可以定义修改状态的方法
    increment() {
      this.count++;
    },
  },
});