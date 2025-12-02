// src/stores/dagStore.ts
import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import type { DAGResponseDTO } from '../types/dag';

export const useDAGStore = defineStore('dag', () => {
  // 状态
  const dags = ref<DAGResponseDTO[]>([]);
  const currentDAG = ref<DAGResponseDTO | null>(null);
  const loading = ref(false);
  const error = ref<string | null>(null);

  // 计算属性
  const dagCount = computed(() => dags.value.length);
  
  const activeDAGs = computed(() => 
    dags.value.filter(dag => dag.status === 'ACTIVE')
  );

  // 动作
  const setDAGs = (newDAGs: DAGResponseDTO[]) => {
    dags.value = newDAGs;
  };

  const setCurrentDAG = (dag: DAGResponseDTO | null) => {
    currentDAG.value = dag;
  };

  const addDAG = (dag: DAGResponseDTO) => {
    dags.value.push(dag);
  };

  const updateDAG = (updatedDAG: DAGResponseDTO) => {
    const index = dags.value.findIndex(d => d.id === updatedDAG.id);
    if (index !== -1) {
      dags.value[index] = updatedDAG;
    }
  };

  const removeDAG = (id: number) => {
    dags.value = dags.value.filter(dag => dag.id !== id);
  };

  const setLoading = (status: boolean) => {
    loading.value = status;
  };

  const setError = (errorMessage: string | null) => {
    error.value = errorMessage;
  };

  // 清除错误
  const clearError = () => {
    error.value = null;
  };

  // 重置状态
  const reset = () => {
    dags.value = [];
    currentDAG.value = null;
    loading.value = false;
    error.value = null;
  };

  return {
    // 状态
    dags,
    currentDAG,
    loading,
    error,
    
    // 计算属性
    dagCount,
    activeDAGs,
    
    // 动作
    setDAGs,
    setCurrentDAG,
    addDAG,
    updateDAG,
    removeDAG,
    setLoading,
    setError,
    clearError,
    reset
  };
});