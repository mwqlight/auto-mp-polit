<template>
  <div class="create-dag">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>创建DAG任务</span>
        </div>
      </template>
      
      <el-form
        ref="dagFormRef"
        :model="dagForm"
        :rules="rules"
        label-width="120px"
        class="dag-form"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="任务名称" prop="name">
              <el-input v-model="dagForm.name" placeholder="请输入任务名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="DAG ID" prop="dagId">
              <el-input v-model="dagForm.dagId" placeholder="请输入DAG ID" />
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item label="描述" prop="description">
          <el-input
            v-model="dagForm.description"
            type="textarea"
            placeholder="请输入任务描述"
            :rows="3"
          />
        </el-form-item>
        
        <el-form-item label="版本" prop="version">
          <el-input v-model="dagForm.version" placeholder="请输入版本号" style="width: 200px;" />
        </el-form-item>
        
        <el-form-item label="任务配置" prop="config">
          <el-input
            v-model="dagForm.config"
            type="textarea"
            placeholder="请输入任务配置（JSON格式）"
            :rows="6"
          />
        </el-form-item>
        
        <el-form-item label="依赖任务">
          <el-select
            v-model="dagForm.dependencies"
            multiple
            placeholder="请选择依赖的任务"
            style="width: 100%;"
          >
            <el-option
              v-for="item in taskOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="submitForm" :loading="submitLoading">
            创建任务
          </el-button>
          <el-button @click="resetForm">重置</el-button>
          <el-button @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'

// 定义表单引用
const dagFormRef = ref<FormInstance>()

// 定义表单数据
const dagForm = reactive({
  name: '',
  dagId: '',
  description: '',
  version: '1.0.0',
  config: '{\n  "parallelism": 4,\n  "timeout": 3600\n}',
  dependencies: [] as string[]
})

// 表单验证规则
const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入任务名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  dagId: [
    { required: true, message: '请输入DAG ID', trigger: 'blur' },
    { pattern: /^[A-Z0-9_-]+$/, message: '只能包含大写字母、数字、下划线和横线', trigger: 'blur' }
  ],
  description: [
    { required: true, message: '请输入任务描述', trigger: 'blur' },
    { min: 5, max: 200, message: '长度在 5 到 200 个字符', trigger: 'blur' }
  ],
  version: [
    { required: true, message: '请输入版本号', trigger: 'blur' },
    { pattern: /^\d+\.\d+\.\d+$/, message: '版本号格式应为 X.Y.Z', trigger: 'blur' }
  ],
  config: [
    { required: true, message: '请输入任务配置', trigger: 'blur' },
    { validator: validateJson, trigger: 'blur' }
  ]
})

// 验证JSON格式
function validateJson(rule: any, value: string, callback: any) {
  if (!value) {
    return callback(new Error('请输入任务配置'))
  }
  try {
    JSON.parse(value)
    callback()
  } catch (error) {
    callback(new Error('JSON格式不正确'))
  }
}

// 依赖任务选项（模拟数据）
const taskOptions = [
  { value: 'TASK-001', label: '数据清洗任务' },
  { value: 'TASK-002', label: '特征提取任务' },
  { value: 'TASK-003', label: '模型训练任务' },
  { value: 'TASK-004', label: '结果评估任务' }
]

// 提交状态
const submitLoading = ref(false)
const router = useRouter()

// 提交表单
const submitForm = async () => {
  if (!dagFormRef.value) return
  await dagFormRef.value.validate((valid, fields) => {
    if (valid) {
      submitLoading.value = true
      // 模拟提交过程
      setTimeout(() => {
        submitLoading.value = false
        ElMessage.success('DAG任务创建成功')
        router.push('/dag-list')
      }, 1500)
    } else {
      console.log('表单验证失败', fields)
      ElMessage.error('请检查表单填写是否正确')
    }
  })
}

// 重置表单
const resetForm = () => {
  if (!dagFormRef.value) return
  dagFormRef.value.resetFields()
}

// 返回上一页
const goBack = () => {
  router.go(-1)
}
</script>

<style scoped>
.create-dag {
  padding: 20px;
}

.card-header {
  font-weight: bold;
  font-size: 18px;
}

.dag-form {
  margin-top: 20px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>