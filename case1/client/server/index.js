import express from 'express'
import cors from 'cors'
import mysql from 'mysql2/promise'

const app = express()
app.use(cors({
  origin: 'http://localhost:5173',
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization']
}))
app.use(express.json())

const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: 'root',
  port: 3308,
  database: 'xiaolidatabases',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
})

// 数据库连接测试接口
// 账号统计接口
app.get('/api/account-stats', async (req, res) => {
  try {
    const [total] = await pool.query('SELECT COUNT(*) AS count FROM life_use_username_password');
    const [vpn] = await pool.query("SELECT COUNT(*) AS count FROM life_use_username_password WHERE isNeedVPN = true");
    const [phone] = await pool.query("SELECT COUNT(*) AS count FROM life_use_username_password WHERE isPhone = true");

    res.json({
      accountCount: total[0].count[0].count,
      vpnAccountCount: vpn[0].count[0],
      phoneAccountCount:phone[0].count[0],
      registrationChartData: {}
    });
  } catch (err) {
    res.status(500).json({
      error: '获取统计信息失败',
      details: err.message
    });
  }
});

const port = 3001
app.listen(port, () => {
  console.log('Server running on port ' + port);
});

app.get('/api/db-test', async (req, res) => {
  try {
    // 验证数据库版本
    const [version] = await pool.query('SELECT VERSION()')
    
    // 验证表结构存在性
    const [tables] = await pool.query("SHOW TABLES LIKE 'life_use_username_password'")
    
    // 获取表结构详情
    const [schema] = await pool.query("DESCRIBE life_use_username_password")

    res.json({
      database_version: version[0]['VERSION()'],
      table_exists: tables.length > 0,
      table_schema: schema
    })
  } catch (err) {
    res.status(500).json({ 
      error: err.message,
      stack: process.env.NODE_ENV === 'development' ? err.stack : undefined
    })
  }
})

// 财务接口
app.get('/api/finance', async (req, res) => {
  try {
    const [rows] = await pool.query('SELECT * FROM finance_records')
    res.json(rows)
  } catch (err) {
    res.status(500).json({ error: err.message })
  }
})

// 账户接口
app.post('/api/accounts', async (req, res) => {
  try {
    const { 
      name,
      username,
      password,
      url,
      has_web,
      has_app,
      need_vpn,
      is_phone,
      vpn
    } = req.body

    const [result] = await pool.query(
      'INSERT INTO life_use_username_password SET ?', 
      {
        name,
        username,
        password,
        url,
        isHasApp: has_app,
        isHasWeb: has_web,
        isNeedVPN: need_vpn,
        isPhone: is_phone,
        vpn
      }
    )
    
    res.json({ id: result.insertId })
  } catch (err) {
    res.status(500).json({ 
      error: err.message,
      stack: process.env.NODE_ENV === 'development' ? err.stack : undefined
    })
  }
})

// 账号分页查询接口
app.get('/api/accounts', async (req, res) => {
  try {
    const { page = 1, pageSize = 10, keyword = '' } = req.query;
    const offset = (page - 1) * pageSize;
    const query = 'SELECT id, name, username, password, isPhone , isNeedVPN ,url ,isHasApp, isHasWeb  FROM life_use_username_password WHERE name LIKE ? LIMIT ? OFFSET ?';
    const countQuery = 'SELECT COUNT(*) AS total FROM life_use_username_password WHERE name LIKE ?';
    const keywordLike = `%${keyword}%`;
    const [rows] = await pool.query(query, [keywordLike, parseInt(pageSize), parseInt(offset)]);
    const [totalResult] = await pool.query(countQuery, [keywordLike]);
    res.json({
      data: rows,
      total: totalResult[0].total
    });
  } catch (err) {
    res.status(500).json({ 
      error: err.message,
      stack: process.env.NODE_ENV === 'development' ? err.stack : undefined
    });
  }
});

// 统计接口
app.get('/stats', async (req, res) => {
  try {
    const [totalAccounts] = await pool.query('SELECT COUNT(*) AS total FROM life_use_username_password');
    const [vpnAccounts] = await pool.query('SELECT COUNT(*) AS vpn_count FROM life_use_username_password WHERE isNeedVPN = 1');
    const [phoneAccounts] = await pool.query('SELECT COUNT(*) AS phone_count FROM life_use_username_password WHERE isPhone = 1');
    const [appAccounts] = await pool.query('SELECT COUNT(*) AS app_count FROM life_use_username_password WHERE isHasApp = 1');
    res.json({
      total_accounts: totalAccounts[0].total,
      vpn_accounts: vpnAccounts[0].vpn_count,
      phone_accounts: phoneAccounts[0].phone_count,
      app_accounts: appAccounts[0].app_count
    });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

export const handler = app