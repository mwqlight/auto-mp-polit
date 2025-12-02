package com.autodag.controller;

import com.autodag.dto.request.DAGRequestDTO;
import com.autodag.dto.response.DAGResponseDTO;
import com.autodag.dto.response.ApiResponse;
import com.autodag.service.DAGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dags")
public class DAGController {
    
    @Autowired
    private DAGService dagService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<DAGResponseDTO>> createDAG(@RequestBody DAGRequestDTO dagRequestDTO) {
        DAGResponseDTO response = dagService.createDAG(dagRequestDTO);
        return ResponseEntity.ok(ApiResponse.success("DAG created successfully", response));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DAGResponseDTO>> getDAGById(@PathVariable Long id) {
        DAGResponseDTO response = dagService.getDAGById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping("/dag-id/{dagId}")
    public ResponseEntity<ApiResponse<DAGResponseDTO>> getDAGByDagId(@PathVariable String dagId) {
        DAGResponseDTO response = dagService.getDAGByDagId(dagId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<DAGResponseDTO>>> getAllDAGs() {
        List<DAGResponseDTO> response = dagService.getAllDAGs();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DAGResponseDTO>> updateDAG(@PathVariable Long id, @RequestBody DAGRequestDTO dagRequestDTO) {
        DAGResponseDTO response = dagService.updateDAG(id, dagRequestDTO);
        return ResponseEntity.ok(ApiResponse.success("DAG updated successfully", response));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDAG(@PathVariable Long id) {
        dagService.deleteDAG(id);
        return ResponseEntity.ok(ApiResponse.success("DAG deleted successfully", null));
    }
}