package com.autodag.service.impl;

import com.autodag.dto.request.CreateDagRequest;
import com.autodag.dto.response.DagResponse;
import com.autodag.entity.DagTask;
import com.autodag.entity.DagStatus;
import com.autodag.repository.DagTaskRepository;
import com.autodag.service.DagTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DagTaskServiceImpl implements DagTaskService {
    
    private final DagTaskRepository dagTaskRepository;
    
    public DagTaskServiceImpl(DagTaskRepository dagTaskRepository) {
        this.dagTaskRepository = dagTaskRepository;
    }
    
    @Override
    public DagResponse createDag(CreateDagRequest request) {
        // 检查DAG ID是否已存在
        if (dagTaskRepository.existsByDagId(request.getDagId())) {
            throw new RuntimeException("DAG ID already exists: " + request.getDagId());
        }
        
        // 创建DAG任务实体
        DagTask dagTask = new DagTask();
        dagTask.setDagId(request.getDagId());
        dagTask.setName(request.getName());
        dagTask.setDescription(request.getDescription());
        dagTask.setCurrentVersion(request.getVersion());
        dagTask.setConfig(request.getConfig());
        dagTask.setStatus(DagStatus.PENDING);
        
        // 保存到数据库
        DagTask savedTask = dagTaskRepository.save(dagTask);
        
        // 转换为响应DTO
        return convertToResponse(savedTask);
    }
    
    @Override
    public List<DagResponse> getAllDags() {
        return dagTaskRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public DagResponse getDagById(Long id) {
        DagTask dagTask = dagTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DAG task not found with id: " + id));
        return convertToResponse(dagTask);
    }
    
    @Override
    public DagResponse updateDag(Long id, CreateDagRequest request) {
        DagTask existingTask = dagTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DAG task not found with id: " + id));
        
        // 更新字段
        existingTask.setName(request.getName());
        existingTask.setDescription(request.getDescription());
        existingTask.setConfig(request.getConfig());
        existingTask.setUpdatedAt(LocalDateTime.now());
        
        // 如果版本号不同，则更新版本号
        if (!existingTask.getCurrentVersion().equals(request.getVersion())) {
            existingTask.setCurrentVersion(request.getVersion());
        }
        
        // 保存更新
        DagTask updatedTask = dagTaskRepository.save(existingTask);
        return convertToResponse(updatedTask);
    }
    
    @Override
    public void deleteDag(Long id) {
        if (!dagTaskRepository.existsById(id)) {
            throw new RuntimeException("DAG task not found with id: " + id);
        }
        dagTaskRepository.deleteById(id);
    }
    
    /**
     * 将DagTask实体转换为DagResponse DTO
     */
    private DagResponse convertToResponse(DagTask dagTask) {
        DagResponse response = new DagResponse();
        BeanUtils.copyProperties(dagTask, response);
        // 状态枚举转换为字符串
        if (dagTask.getStatus() != null) {
            response.setStatus(dagTask.getStatus().name());
        }
        return response;
    }
}