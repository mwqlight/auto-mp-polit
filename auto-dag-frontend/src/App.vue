<script setup lang="ts">
import { RouterView, useRouter } from 'vue-router'
import { ref } from 'vue'
import { ElMenu, ElMenuItem, ElIcon } from 'element-plus'
import { Document, Menu as MenuIcon, InfoFilled } from '@element-plus/icons-vue'

// 导航菜单激活项
const activeIndex = ref('1')
const router = useRouter()

// 菜单激活回调
const handleSelect = (key: string) => {
  switch (key) {
    case '1':
      router.push('/')
      break
    case '2':
      router.push('/dag-list')
      break
    case '3':
      router.push('/create-dag')
      break
    case '4':
      router.push('/about')
      break
  }
}
</script>

<template>
  <div class="app-container">
    <!-- 侧边栏 -->
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      mode="vertical"
      @select="handleSelect"
    >
      <el-menu-item index="1">
        <el-icon><MenuIcon /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="2">
        <el-icon><Document /></el-icon>
        <span>DAG任务列表</span>
      </el-menu-item>
      <el-menu-item index="3">
        <el-icon><MenuIcon /></el-icon>
        <span>创建DAG任务</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon><InfoFilled /></el-icon>
        <span>关于我们</span>
      </el-menu-item>
    </el-menu>

    <!-- 主内容区域 -->
    <div class="main-content">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.app-container {
  display: flex;
  height: 100vh;
}

.el-menu-vertical-demo {
  width: 200px;
  min-height: 100%;
}

.main-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .el-menu-vertical-demo {
    width: 60px;
  }
  
  .el-menu-vertical-demo span {
    display: none;
  }
  
  .el-menu-vertical-demo .el-menu-item {
    padding-left: 20px !important;
  }
}
</style>
