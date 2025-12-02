// src/api/modules/dag.ts
import apiClient from '../index';
import { 
  DAGRequestDTO, 
  DAGResponseDTO, 
  ApiResponse 
} from '../../types/dag';

// 创建DAG
export const createDAG = (data: DAGRequestDTO): Promise<ApiResponse<DAGResponseDTO>> => {
  return apiClient.post<ApiResponse<DAGResponseDTO>>('/v1/dags', data);
};

// 获取DAG详情
export const getDAGById = (id: number): Promise<ApiResponse<DAGResponseDTO>> => {
  return apiClient.get<ApiResponse<DAGResponseDTO>>(`/v1/dags/${id}`);
};

// 根据DAG ID获取DAG详情
export const getDAGByDagId = (dagId: string): Promise<ApiResponse<DAGResponseDTO>> => {
  return apiClient.get<ApiResponse<DAGResponseDTO>>(`/v1/dags/dag-id/${dagId}`);
};

// 获取所有DAG
export const getAllDAGs = (): Promise<ApiResponse<DAGResponseDTO[]>> => {
  return apiClient.get<ApiResponse<DAGResponseDTO[]>>('/v1/dags');
};

// 更新DAG
export const updateDAG = (id: number, data: DAGRequestDTO): Promise<ApiResponse<DAGResponseDTO>> => {
  return apiClient.put<ApiResponse<DAGResponseDTO>>(`/v1/dags/${id}`, data);
};

// 删除DAG
export const deleteDAG = (id: number): Promise<ApiResponse<void>> => {
  return apiClient.delete<ApiResponse<void>>(`/v1/dags/${id}`);
};