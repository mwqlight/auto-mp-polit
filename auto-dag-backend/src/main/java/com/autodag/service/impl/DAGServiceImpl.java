package com.autodag.service.impl;

import com.autodag.entity.DAG;
import com.autodag.entity.DAGVersion;
import com.autodag.repository.DAGRepository;
import com.autodag.repository.DAGVersionRepository;
import com.autodag.dto.request.DAGRequestDTO;
import com.autodag.dto.response.DAGResponseDTO;
import com.autodag.service.DAGService;
import com.autodag.mapper.DAGMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DAGServiceImpl implements DAGService {
    
    @Autowired
    private DAGRepository dagRepository;
    
    @Autowired
    private DAGVersionRepository dagVersionRepository;
    
    @Override
    public DAGResponseDTO createDAG(DAGRequestDTO dagRequestDTO) {
        // 创建DAG实体
        DAG dag = DAGMapper.INSTANCE.dagRequestDTOToDAG(dagRequestDTO);
        DAG savedDag = dagRepository.save(dag);
        
        // 创建DAG版本实体
        DAGVersion dagVersion = new DAGVersion();
        dagVersion.setDagId(savedDag.getId());
        dagVersion.setVersionNumber(dagRequestDTO.getVersion());
        dagVersion.setDagDefinition(""); // 在实际实现中应该序列化DAG定义
        dagVersion.setCreatedAt(LocalDateTime.now());
        dagVersion.setCreatedBy("system");
        
        dagVersionRepository.save(dagVersion);
        
        // 转换为响应DTO
        DAGResponseDTO responseDTO = DAGMapper.INSTANCE.dagToDAGResponseDTO(savedDag);
        responseDTO.setCurrentVersion(dagVersion.getVersionNumber());
        
        return responseDTO;
    }
    
    @Override
    public DAGResponseDTO getDAGById(Long id) {
        // 从数据库查询DAG实体
        DAG dag = dagRepository.findById(id).orElse(null);
        if (dag == null) {
            return null;
        }
        
        // 转换为响应DTO
        DAGResponseDTO responseDTO = DAGMapper.INSTANCE.dagToDAGResponseDTO(dag);
        
        // 获取当前版本信息
        List<DAGVersion> versions = dagVersionRepository.findByDagId(id);
        if (!versions.isEmpty()) {
            // 获取最新的版本
            DAGVersion currentVersion = versions.stream()
                .max((v1, v2) -> v1.getCreatedAt().compareTo(v2.getCreatedAt()))
                .orElse(null);
            if (currentVersion != null) {
                responseDTO.setCurrentVersion(currentVersion.getVersionNumber());
            }
        }
        
        return responseDTO;
    }
    
    @Override
    public DAGResponseDTO getDAGByDagId(String dagId) {
        // 从数据库查询DAG实体
        Optional<DAG> optionalDag = dagRepository.findByDagId(dagId);
        if (!optionalDag.isPresent()) {
            return null;
        }
        
        DAG dag = optionalDag.get();
        
        // 转换为响应DTO
        DAGResponseDTO responseDTO = DAGMapper.INSTANCE.dagToDAGResponseDTO(dag);
        
        // 获取当前版本信息
        List<DAGVersion> versions = dagVersionRepository.findByDagId(dag.getId());
        if (!versions.isEmpty()) {
            // 获取最新的版本
            DAGVersion currentVersion = versions.stream()
                .max((v1, v2) -> v1.getCreatedAt().compareTo(v2.getCreatedAt()))
                .orElse(null);
            if (currentVersion != null) {
                responseDTO.setCurrentVersion(currentVersion.getVersionNumber());
            }
        }
        
        return responseDTO;
    }
    
    @Override
    public List<DAGResponseDTO> getAllDAGs() {
        // 从数据库查询所有DAG实体
        List<DAG> dags = dagRepository.findAll();
        
        // 转换为响应DTO列表
        return dags.stream().map(dag -> {
            DAGResponseDTO responseDTO = DAGMapper.INSTANCE.dagToDAGResponseDTO(dag);
            
            // 获取当前版本信息
            List<DAGVersion> versions = dagVersionRepository.findByDagId(dag.getId());
            if (!versions.isEmpty()) {
                // 获取最新的版本
                DAGVersion currentVersion = versions.stream()
                    .max((v1, v2) -> v1.getCreatedAt().compareTo(v2.getCreatedAt()))
                    .orElse(null);
                if (currentVersion != null) {
                    responseDTO.setCurrentVersion(currentVersion.getVersionNumber());
                }
            }
            
            return responseDTO;
        }).collect(Collectors.toList());
    }
    
    @Override
    public DAGResponseDTO updateDAG(Long id, DAGRequestDTO dagRequestDTO) {
        // 实际实现中需要更新数据库记录并返回更新后的DTO
        return new DAGResponseDTO();
    }
    
    @Override
    public void deleteDAG(Long id) {
        // 实际实现中需要删除数据库记录
    }
}