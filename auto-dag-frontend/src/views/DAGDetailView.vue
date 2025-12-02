<!-- src/views/DAGDetailView.vue -->
<template>
  <div class="dag-detail" v-loading="loading">
    <el-page-header @back="goBack">
      <template #content>
        <span>DAG详情</span>
      </template>
    </el-page-header>
    
    <div v-if="dagDetail" class="content">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ dagDetail.name }}</span>
            <el-tag :type="getStatusTagType(dagDetail.status)">
              {{ dagDetail.status }}
            </el-tag>
          </div>
        </template>
        
        <el-descriptions :column="2" border>
          <el-descriptions-item label="ID">{{ dagDetail.id }}</el-descriptions-item>
          <el-descriptions-item label="DAG ID">{{ dagDetail.dagId }}</el-descriptions-item>
          <el-descriptions-item label="名称">{{ dagDetail.name }}</el-descriptions-item>
          <el-descriptions-item label="版本">{{ dagDetail.currentVersion }}</el-descriptions-item>
          <el-descriptions-item label="创建者">{{ dagDetail.createdBy }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ dagDetail.createdAt }}</el-descriptions-item>
          <el-descriptions-item label="更新时间">{{ dagDetail.updatedAt }}</el-descriptions-item>
          <el-descriptions-item label="描述" :span="2">{{ dagDetail.description }}</el-descriptions-item>
        </el-descriptions>
      </el-card>
      
      <el-tabs v-model="activeTab" class="detail-tabs">
        <el-tab-pane label="基本信息" name="basic">
          <div class="tab-content">
            <h3>基本信息</h3>
            <p>这里显示DAG的基本信息</p>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="节点信息" name="nodes">
          <div class="tab-content">
            <h3>节点信息</h3>
            <p>这里显示DAG的节点信息</p>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="边信息" name="edges">
          <div class="tab-content">
            <h3>边信息</h3>
            <p>这里显示DAG的边信息</p>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="调度信息" name="schedule">
          <div class="tab-content">
            <h3>调度信息</h3>
            <p>这里显示DAG的调度信息</p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { getDAGById } from '../api/modules/dag';
import type { DAGResponseDTO, ApiResponse } from '../types/dag';

// 路由相关
const route = useRoute();
const router = useRouter();

// 响应式数据
const loading = ref(false);
const dagDetail = ref<DAGResponseDTO | null>(null);
const activeTab = ref('basic');

// 获取状态标签类型
const getStatusTagType = (status: string) => {
  switch (status) {
    case 'ACTIVE':
      return 'success';
    case 'INACTIVE':
      return 'warning';
    case 'ERROR':
      return 'danger';
    default:
      return 'info';
  }
};

// 返回上一页
const goBack = () => {
  router.push('/dag-list');
};

// 获取DAG详情
const fetchDAGDetail = async (id: number) => {
  try {
    loading.value = true;
    const response: ApiResponse<DAGResponseDTO> = await getDAGById(id);
    if (response.code === 200) {
      dagDetail.value = response.data;
    } else {
      ElMessage.error(response.message || '获取DAG详情失败');
    }
  } catch (error) {
    ElMessage.error('获取DAG详情失败');
  } finally {
    loading.value = false;
  }
};

// 组件挂载时获取数据
onMounted(() => {
  const id = Number(route.params.id);
  if (id) {
    fetchDAGDetail(id);
  } else {
    ElMessage.error('无效的DAG ID');
    router.push('/dag-list');
  }
});
</script>

<style scoped>
.dag-detail {
  padding: 20px;
}

.content {
  margin-top: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.detail-tabs {
  margin-top: 20px;
}

.tab-content {
  padding: 20px 0;
}

.tab-content h3 {
  margin-top: 0;
}
</style>