<template>
  <div class="about">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>关于我们</span>
        </div>
      </template>
      
      <div class="content">
        <h2>并行计算任务驾驶舱平台</h2>
        
        <el-divider />
        
        <div class="section">
          <h3>平台介绍</h3>
          <p>
            并行计算任务驾驶舱平台是一个专为高性能计算环境设计的任务管理系统，
            支持复杂DAG（有向无环图）任务的定义、调度和监控。
          </p>
        </div>
        
        <el-divider />
        
        <div class="section">
          <h3>核心功能</h3>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-card class="feature-card">
                <template #header>
                  <div class="feature-header">
                    <el-icon><Document /></el-icon>
                    <span>DAG任务管理</span>
                  </div>
                </template>
                <p>可视化创建和管理复杂的DAG任务，支持节点依赖关系定义。</p>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card class="feature-card">
                <template #header>
                  <div class="feature-header">
                    <el-icon><Monitor /></el-icon>
                    <span>实时监控</span>
                  </div>
                </template>
                <p>实时监控任务执行状态，提供详细的执行日志和性能指标。</p>
              </el-card>
            </el-col>
          </el-row>
          
          <el-row :gutter="20" style="margin-top: 20px;">
            <el-col :span="12">
              <el-card class="feature-card">
                <template #header>
                  <div class="feature-header">
                    <el-icon><Setting /></el-icon>
                    <span>智能调度</span>
                  </div>
                </template>
                <p>基于资源负载的智能任务调度算法，最大化集群利用率。</p>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card class="feature-card">
                <template #header>
                  <div class="feature-header">
                    <el-icon><DataAnalysis /></el-icon>
                    <span>数据分析</span>
                  </div>
                </template>
                <p>提供任务执行历史数据分析，帮助优化任务配置。</p>
              </el-card>
            </el-col>
          </el-row>
        </div>
        
        <el-divider />
        
        <div class="section">
          <h3>技术支持</h3>
          <p>
            平台采用现代化的前后端分离架构，后端基于Spring Boot 3.0，
            前端基于Vue 3 + TypeScript，确保系统的高性能和可维护性。
          </p>
          <ul>
            <li>后端技术栈：Spring Boot 3.0, Spring Security, JPA, MySQL</li>
            <li>前端技术栈：Vue 3, TypeScript, Element Plus, AntV G6</li>
            <li>部署方案：Docker容器化部署，支持Kubernetes编排</li>
          </ul>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { Document, Monitor, Setting, DataAnalysis } from '@element-plus/icons-vue'
</script>

<style scoped>
.about {
  padding: 20px;
}

.card-header {
  font-weight: bold;
  font-size: 18px;
}

.content h2 {
  text-align: center;
  color: #409eff;
  margin-bottom: 30px;
}

.section {
  margin-bottom: 30px;
}

.section h3 {
  color: #67c23a;
  margin-bottom: 15px;
}

.feature-card {
  height: 100%;
}

.feature-header {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.feature-header .el-icon {
  margin-right: 10px;
  font-size: 18px;
  color: #409eff;
}

.el-divider {
  margin: 20px 0;
}

ul {
  padding-left: 20px;
}

li {
  margin-bottom: 10px;
}
</style>
