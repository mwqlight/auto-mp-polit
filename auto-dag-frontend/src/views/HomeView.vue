<!-- src/views/HomeView.vue -->
<template>
  <div class="home">
    <el-card class="welcome-card">
      <template #header>
        <div class="card-header">
          <span>欢迎使用并行计算任务驾驶舱平台</span>
        </div>
      </template>
      
      <div class="content">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-statistic title="总任务数" :value="statistics.totalTasks" />
          </el-col>
          <el-col :span="8">
            <el-statistic title="运行中任务" :value="statistics.runningTasks" />
          </el-col>
          <el-col :span="8">
            <el-statistic title="已完成任务" :value="statistics.completedTasks" />
          </el-col>
        </el-row>
        
        <div class="quick-actions">
          <h3>快捷操作</h3>
          <el-button type="primary" @click="goToCreateDAG">
            <el-icon><Plus /></el-icon>
            创建新DAG任务
          </el-button>
          <el-button @click="goToListDAG">
            <el-icon><List /></el-icon>
            查看所有DAG任务
          </el-button>
        </div>
      </div>
    </el-card>
    
    <el-card class="recent-tasks">
      <template #header>
        <div class="card-header">
          <span>最近任务</span>
        </div>
      </template>
      
      <el-table :data="recentTasks" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="任务名称" />
        <el-table-column prop="status" label="状态" width="120">
          <template #default="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createdAt" label="创建时间" width="200" />
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button size="small" @click="viewTaskDetails(scope.row.id)">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Plus, List } from '@element-plus/icons-vue'
import type { DAGResponseDTO } from '../types/dag'

// 路由
const router = useRouter()

// 统计数据
const statistics = ref({
  totalTasks: 128,
  runningTasks: 24,
  completedTasks: 104
})

// 最近任务数据
const recentTasks = ref([
  {
    id: 1,
    name: '数据分析任务',
    status: 'ACTIVE',
    createdAt: '2023-05-15 14:30:22'
  },
  {
    id: 2,
    name: '模型训练任务',
    status: 'RUNNING',
    createdAt: '2023-05-14 09:15:45'
  },
  {
    id: 3,
    name: '报表生成任务',
    status: 'COMPLETED',
    createdAt: '2023-05-13 16:42:18'
  }
])

// 获取状态标签类型
const getStatusTagType = (status: string) => {
  switch (status) {
    case 'ACTIVE':
      return 'success'
    case 'RUNNING':
      return 'warning'
    case 'COMPLETED':
      return 'info'
    default:
      return 'info'
  }
}

// 跳转到创建DAG页面
const goToCreateDAG = () => {
  router.push('/dag-create')
}

// 跳转到DAG列表页面
const goToListDAG = () => {
  router.push('/dag-list')
}

// 查看任务详情
const viewTaskDetails = (id: number) => {
  router.push(`/dag-detail/${id}`)
}

// 组件挂载时获取数据
onMounted(() => {
  // 这里可以调用API获取实际数据
  console.log('加载首页数据')
})
</script>

<style scoped>
.home {
  padding: 20px;
}

.welcome-card {
  margin-bottom: 20px;
}

.card-header {
  font-weight: bold;
  font-size: 18px;
}

.content {
  padding: 20px 0;
}

.quick-actions {
  margin-top: 30px;
}

.quick-actions h3 {
  margin-bottom: 15px;
}

.quick-actions .el-button {
  margin-right: 10px;
}

.recent-tasks {
  margin-top: 20px;
}
</style>
