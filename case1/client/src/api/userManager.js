// 账号管理API模块
const BASE_URL = 'http://localhost:3001/api';

export default {
  // 获取账号统计数据
  async getAccountStats() {
    const response = await fetch(`${BASE_URL}/account-stats`);
    return response.json();
  },

  // 分页获取账号列表
  async getAccounts(params) {
    const query = new URLSearchParams({
      page: params.page,
      pageSize: params.pageSize,
      keyword: params.keyword || ''
    });
    
    const response = await fetch(`${BASE_URL}/accounts?${query}`);
    return response.json();
  },

  // 删除账号
  async deleteAccount(accountId) {
    const response = await fetch(`${BASE_URL}/accounts/${accountId}`, {
      method: 'DELETE'
    });
    return response.json();
  }
}