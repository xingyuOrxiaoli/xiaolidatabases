import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import express from 'express'
import cors from 'cors'

export default defineConfig({
  base: './',
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  },
  build: {
    outDir: '../server/public'
  },
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:3001',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/api/, '')
      }
    },
    configureServer(server) {
      const app = express()
      app.use(cors())
      app.use(express.json())
      app.use('/api', (req, res, next) => {
        import('./server/index.js').then(({ handler }) => handler(req, res, next))
      })
    }
  }
})