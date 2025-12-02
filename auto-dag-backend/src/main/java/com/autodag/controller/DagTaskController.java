package com.autodag.controller;

import com.autodag.dto.request.CreateDagRequest;
import com.autodag.dto.response.DagResponse;
import com.autodag.service.DagTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dag-tasks")
@CrossOrigin(origins = "*")
public class DagTaskController {
    
    private final DagTaskService dagTaskService;
    
    public DagTaskController(DagTaskService dagTaskService) {
        this.dagTaskService = dagTaskService;
        }
    
    /**
     * 创建DAG任务
     */
    @PostMapping
    public ResponseEntity<DagResponse> createDag(@RequestBody CreateDagRequest request) {
        try {
            DagResponse response = dagTaskService.createDag(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create DAG task: " + e.getMessage());
        }
    }
    
    /**
     * 获取所有DAG任务
     */
    @GetMapping
    public ResponseEntity<List<DagResponse>> getAllDags() {
        try {
            List<DagResponse> responses = dagTaskService.getAllDags();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve DAG tasks: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID获取DAG任务
     */
    @GetMapping("/{id}")
    public ResponseEntity<DagResponse> getDagById(@PathVariable Long id) {
        try {
            DagResponse response = dagTaskService.getDagById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve DAG task: " + e.getMessage());
        }
    }
    
    /**
     * 更新DAG任务
     */
    @PutMapping("/{id}")
    public ResponseEntity<DagResponse> updateDag(@PathVariable Long id, @RequestBody CreateDagRequest request) {
        try {
            DagResponse response = dagTaskService.updateDag(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update DAG task: " + e.getMessage());
        }
    }
    
    /**
     * 删除DAG任务
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDag(@PathVariable Long id) {
        try {
            dagTaskService.deleteDag(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete DAG task: " + e.getMessage());
        }
    }
}