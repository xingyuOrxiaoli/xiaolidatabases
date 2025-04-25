import axios from 'axios'

export const getAccounts = async (params) => {
  try {
    const response = await axios.get('http://localhost:3000/accounts', { params })
    return response.data
  } catch (error) {
    console.error('获取账号数据失败:', error)
    throw error
  }
}