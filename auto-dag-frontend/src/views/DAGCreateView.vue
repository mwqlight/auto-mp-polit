<!-- src/views/DAGCreateView.vue -->
<template>
  <div class="dag-create">
    <h1>创建DAG任务</h1>
    <el-form
      :model="dagForm"
      :rules="rules"
      ref="dagFormRef"
      label-width="120px"
      style="max-width: 800px"
    >
      <el-form-item label="DAG ID" prop="dagId">
        <el-input v-model="dagForm.dagId" placeholder="请输入DAG ID" />
      </el-form-item>
      
      <el-form-item label="名称" prop="name">
        <el-input v-model="dagForm.name" placeholder="请输入DAG名称" />
      </el-form-item>
      
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="dagForm.description"
          type="textarea"
          placeholder="请输入DAG描述"
          :rows="3"
        />
      </el-form-item>
      
      <el-form-item label="版本" prop="version">
        <el-input v-model="dagForm.version" placeholder="请输入版本号" />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" @click="submitForm" :loading="submitLoading">
          创建DAG
        </el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button @click="router.push('/dag-list')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, FormInstance, FormRules } from 'element-plus';
import { createDAG } from '../api/modules/dag';
import type { DAGRequestDTO, ApiResponse } from '../types/dag';

// 表单引用
const dagFormRef = ref<FormInstance>();

// 路由
const router = useRouter();

// 表单数据
const dagForm = reactive<DAGRequestDTO>({
  dagId: '',
  name: '',
  description: '',
  version: '1.0.0',
  nodes: [],
  edges: []
});

// 提交状态
const submitLoading = ref(false);

// 表单验证规则
const rules = reactive<FormRules>({
  dagId: [
    { required: true, message: '请输入DAG ID', trigger: 'blur' },
    { min: 3, max: 50, message: '长度应在3到50个字符之间', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入DAG名称', trigger: 'blur' },
    { min: 2, max: 100, message: '长度应在2到100个字符之间', trigger: 'blur' }
  ],
  version: [
    { required: true, message: '请输入版本号', trigger: 'blur' },
    { pattern: /^\d+\.\d+\.\d+$/, message: '版本号格式应为 X.Y.Z', trigger: 'blur' }
  ]
});

// 提交表单
const submitForm = async () => {
  if (!dagFormRef.value) return;
  
  await dagFormRef.value.validate(async (valid, fields) => {
    if (valid) {
      try {
        submitLoading.value = true;
        const response: ApiResponse<any> = await createDAG(dagForm);
        if (response.code === 200) {
          ElMessage.success('DAG创建成功');
          router.push('/dag-list');
        } else {
          ElMessage.error(response.message || '创建失败');
        }
      } catch (error) {
        ElMessage.error('创建失败');
      } finally {
        submitLoading.value = false;
      }
    } else {
      console.log('表单验证失败', fields);
      ElMessage.error('请检查表单填写是否正确');
    }
  });
};

// 重置表单
const resetForm = () => {
  if (!dagFormRef.value) return;
  dagFormRef.value.resetFields();
};
</script>

<style scoped>
.dag-create {
  padding: 20px;
}
</style>