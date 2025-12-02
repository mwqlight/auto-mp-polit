<!-- src/components/DAG/DAGVisualizer.vue -->
<template>
  <div class="dag-visualizer" ref="containerRef"></div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, watch } from 'vue';
import G6 from '@antv/g6';
import type { DAGResponseDTO } from '../../types/dag';

// 定义组件属性
interface Props {
  dagData?: DAGResponseDTO | null;
}

const props = withDefaults(defineProps<Props>(), {
  dagData: null
});

// 图实例和容器引用
const graph = ref<any>(null);
const containerRef = ref<HTMLDivElement | null>(null);

// 初始化图表
const initGraph = () => {
  if (!containerRef.value) return;

  // 销毁现有的图表实例
  if (graph.value) {
    graph.value.destroy();
  }

  // 获取容器尺寸
  const width = containerRef.value.clientWidth || 800;
  const height = Math.max(containerRef.value.clientHeight || 600, 500);

  // 创建新的图表实例
  graph.value = new G6.Graph({
    container: containerRef.value,
    width,
    height,
    modes: {
      default: ['drag-canvas', 'zoom-canvas', 'drag-node']
    },
    layout: {
      type: 'dagre',
      rankdir: 'TB',
      nodesep: 30,
      ranksep: 50
    },
    defaultNode: {
      type: 'rect',
      size: [120, 40],
      style: {
        radius: 6,
        fill: '#fff',
        stroke: '#5B8FF9',
        lineWidth: 1
      },
      labelCfg: {
        style: {
          fill: '#000',
          fontSize: 12
        }
      }
    },
    defaultEdge: {
      type: 'polyline',
      style: {
        stroke: '#91d5ff',
        lineWidth: 1,
        endArrow: {
          path: 'M 0,0 L 8,4 L 8,-4 Z',
          fill: '#91d5ff'
        }
      },
      labelCfg: {
        refY: -10,
        refX: 50,
        style: {
          fill: '#333',
          fontSize: 10
        }
      }
    }
  });
};

// 渲染DAG数据
const renderDAG = () => {
  if (!graph.value || !props.dagData) return;

  // 转换节点数据
  const nodes = props.dagData.nodes.map(node => ({
    id: node.id.toString(),
    label: node.name,
    description: node.description
  }));

  // 转换边数据
  const edges = props.dagData.edges.map(edge => ({
    source: edge.sourceNodeId.toString(),
    target: edge.targetNodeId.toString(),
    label: edge.label || ''
  }));

  // 设置数据并渲染
  graph.value.data({ nodes, edges });
  graph.value.render();
};

// 处理窗口大小变化
const handleResize = () => {
  if (graph.value && containerRef.value) {
    graph.value.changeSize(
      containerRef.value.clientWidth || 800,
      containerRef.value.clientHeight || 600
    );
  }
};

// 监听DAG数据变化
watch(() => props.dagData, renderDAG, { deep: true });

// 组件挂载
onMounted(() => {
  initGraph();
  renderDAG();
  window.addEventListener('resize', handleResize);
});

// 组件卸载前清理
onUnmounted(() => {
  if (graph.value) {
    graph.value.destroy();
  }
  window.removeEventListener('resize', handleResize);
});
</script>

<style scoped>
.dag-visualizer {
  width: 100%;
  height: 100%;
  min-height: 500px;
  border: 1px solid #e8e8e8;
  border-radius: 4px;
}
</style>