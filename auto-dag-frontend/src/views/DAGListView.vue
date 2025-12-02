<template>
  <div class="dag-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>DAG任务列表</span>
          <el-button type="primary" @click="handleCreate">创建DAG任务</el-button>
        </div>
      </template>
      
      <div class="toolbar">
        <el-input
          v-model="searchText"
          placeholder="搜索DAG任务..."
          style="width: 300px;"
          clearable
        >
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
      </div>
      
      <el-table :data="filteredDAGs" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="任务ID" width="100" />
        <el-table-column prop="name" label="任务名称" width="200" />
        <el-table-column prop="dagId" label="DAG ID" width="150" />
        <el-table-column prop="description" label="描述" />
        <el-table-column prop="currentVersion" label="版本" width="100" />
        <el-table-column prop="status" label="状态" width="120">
          <template #default="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createdAt" label="创建时间" width="180" />
        <el-table-column label="操作" fixed="right" width="200">
          <template #default="scope">
            <el-button size="small" @click="handleView(scope.row)">查看</el-button>
            <el-button size="small" type="primary" @click="handleEdit(scope.row)" :disabled="scope.row.status !== 'ACTIVE'">
              编辑
            </el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Search } from '@element-plus/icons-vue';
// import { getAllDAGs, deleteDAG } from '../api/modules/dag';
// import type { DAGResponseDTO, ApiResponse } from '../types/dag';

// 定义任务状态类型
type DagStatus = 'ACTIVE' | 'INACTIVE' | 'ERROR' | 'PENDING' | 'RUNNING' | 'SUCCESS' | 'FAILED' | 'CANCELLED'

interface DagTask {
  id: number
  dagId: string
  name: string
  description: string
  currentVersion: string
  status: DagStatus
  createdAt: string
}

// 数据响应式变量
// const dagList = ref<DAGResponseDTO[]>([]);
const dagList = ref<DagTask[]>([
  {
    id: 1,
    dagId: 'DAG-001',
    name: '数据处理任务',
    description: '处理用户上传的数据文件',
    currentVersion: '1.0.0',
    status: 'SUCCESS',
    createdAt: '2023-06-01 10:00:00'
  },
  {
    id: 2,
    dagId: 'DAG-002',
    name: '模型训练任务',
    description: '机器学习模型训练流程',
    currentVersion: '2.1.0',
    status: 'RUNNING',
    createdAt: '2023-06-02 14:00:00'
  },
  {
    id: 3,
    dagId: 'DAG-003',
    name: '报表生成任务',
    description: '自动生成业务分析报表',
    currentVersion: '1.2.0',
    status: 'ACTIVE',
    createdAt: '2023-06-03 09:00:00'
  }
]);

const loading = ref(false);
const searchText = ref('');
const router = useRouter();
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(3);

// 计算属性：过滤后的DAG列表
const filteredDAGs = computed(() => {
  if (!searchText.value) {
    return dagList.value;
  }
  const search = searchText.value.toLowerCase();
  return dagList.value.filter(
    (dag) =>
      dag.name.toLowerCase().includes(search) ||
      dag.dagId.toLowerCase().includes(search) ||
      dag.description.toLowerCase().includes(search)
  );
});

// 获取状态标签类型
const getStatusTagType = (status: DagStatus) => {
  switch (status) {
    case 'ACTIVE':
      return 'success';
    case 'INACTIVE':
      return 'warning';
    case 'ERROR':
    case 'FAILED':
      return 'danger';
    case 'RUNNING':
      return 'primary';
    case 'SUCCESS':
      return 'success';
    case 'CANCELLED':
      return 'info';
    default:
      return 'info';
  }
};

// 获取状态文本描述
const getStatusText = (status: DagStatus) => {
  switch (status) {
    case 'ACTIVE':
      return '活跃';
    case 'INACTIVE':
      return '非活跃';
    case 'ERROR':
      return '错误';
    case 'PENDING':
      return '待执行';
    case 'RUNNING':
      return '运行中';
    case 'SUCCESS':
      return '成功';
    case 'FAILED':
      return '失败';
    case 'CANCELLED':
      return '已取消';
    default:
      return status;
  }
};

// // 获取DAG列表
// const fetchDAGList = async () => {
//   try {
//     loading.value = true;
//     const response: ApiResponse<DAGResponseDTO[]> = await getAllDAGs();
//     if (response.code === 200) {
//       dagList.value = response.data;
//       total.value = response.data.length; // 假设API返回总数
//     } else {
//       ElMessage.error(response.message || '获取DAG列表失败');
//     }
//   } catch (error) {
//     ElMessage.error('获取DAG列表失败');
//   } finally {
//     loading.value = false;
//   }
// };

// 处理创建DAG
const handleCreate = () => {
  router.push('/create-dag');
};

// 处理查看DAG
const handleView = (row: DagTask) => {
  router.push(`/dag-detail/${row.id}`);
};

// 处理编辑DAG
const handleEdit = (row: DagTask) => {
  router.push(`/dag-edit/${row.id}`);
};

// 处理删除DAG
const handleDelete = (row: DagTask) => {
  ElMessageBox.confirm(
    `确定要删除DAG任务 "${row.name}" 吗？此操作不可恢复。`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      // 模拟删除操作
      ElMessage.success('删除成功');
      // 实际项目中应该调用API删除
      // try {
      //   const response: ApiResponse<void> = await deleteDAG(row.id);
      //   if (response.code === 200) {
      //     ElMessage.success('删除成功');
      //     fetchDAGList(); // 刷新列表
      //   } else {
      //     ElMessage.error(response.message || '删除失败');
      //   }
      // } catch (error) {
      //   ElMessage.error('删除失败');
      // }
    })
    .catch(() => {
      // 用户取消删除
    });
};

// 处理分页大小变化
const handleSizeChange = (val: number) => {
  pageSize.value = val;
  console.log(`每页 ${val} 条`);
};

// 处理当前页变化
const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  console.log(`当前页: ${val}`);
};

// 组件挂载时获取数据
onMounted(() => {
  // fetchDAGList();
});
</script>

<style scoped>
.dag-list {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.toolbar {
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-end;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>