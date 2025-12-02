// src/types/dag.ts

export interface ApiResponse<T> {
  code: number;
  message: string;
  data: T;
  timestamp: string;
}

export interface DAGRequestDTO {
  dagId: string;
  name: string;
  description: string;
  version: string;
  schedule?: ScheduleConfig;
  nodes: Node[];
  edges: Edge[];
  globalConfigs?: GlobalConfigs;
  mapReduceConfig?: MapReduceConfig;
}

export interface ScheduleConfig {
  type: string;
  expression: string;
  timezone: string;
  retryPolicy?: RetryPolicy;
}

export interface RetryPolicy {
  maxRetries: number;
  retryInterval: string;
  backoffMultiplier: number;
}

export interface Node {
  nodeId: string;
  name: string;
  type: string;
  position: Position;
  taskDefinition: TaskDefinition;
  resourceRequirements: ResourceRequirements;
  executionConfig: ExecutionConfig;
}

export interface Position {
  x: number;
  y: number;
  z: number;
}

export interface TaskDefinition {
  artifactId: string;
  className: string;
  runtime: string;
  inputSchema: any;
  outputSchema: any;
}

export interface ResourceRequirements {
  cpuCores: number;
  memoryMB: number;
  diskGB: number;
  gpuUnits: number;
  networkBandwidth: string;
  gpuType: string;
}

export interface ExecutionConfig {
  timeout: string;
  parallelism: number;
  isMapStage: boolean;
  isReduceStage: boolean;
  memoryIntensive: boolean;
  partitionStrategy: string;
}

export interface Edge {
  from: string;
  to: string;
  dataMapping: any;
  condition: string;
}

export interface GlobalConfigs {
  defaultResourcePool: string;
  dataRetentionPolicy: string;
  notificationChannels: string[];
  observabilityLevel: string;
}

export interface MapReduceConfig {
  enabled: boolean;
  mapParallelismDefault: number;
  reduceParallelismDefault: number;
  shuffleStrategy: string;
  compressionEnabled: boolean;
  spillThresholdMB: number;
}

export interface DAGResponseDTO {
  id: number;
  dagId: string;
  name: string;
  description: string;
  currentVersion: string;
  status: string;
  scheduleInfo?: ScheduleInfo;
  validationResults?: ValidationResults;
  resourceEstimates?: ResourceEstimates;
  dataFlowAnalysis?: DataFlowAnalysis;
  createdAt: string;
  updatedAt: string;
  createdBy: string;
}

export interface ScheduleInfo {
  type: string;
  expression: string;
  timezone: string;
  nextRunTime: string;
}

export interface ValidationResults {
  isValid: boolean;
  errors: string[];
  warnings: string[];
  dependencyChecks: DependencyCheck[];
}

export interface DependencyCheck {
  artifactId: string;
  status: string;
  message: string;
}

export interface ResourceEstimates {
  totalCpuCores: number;
  totalMemoryMB: number;
  totalDiskGB: number;
  estimatedRuntime: string;
  resourcePool: string;
}

export interface DataFlowAnalysis {
  totalNodes: number;
  totalEdges: number;
  nodeMetrics: NodeMetrics[];
  criticalPaths: CriticalPath[];
}

export interface NodeMetrics {
  nodeId: string;
  nodeName: string;
  type: string;
  inDegree: number;
  outDegree: number;
  estimatedRuntime: string;
  resourceProfile: string;
}

export interface CriticalPath {
  nodeSequence: string[];
  totalTime: string;
  bottleneckNodes: string;
}