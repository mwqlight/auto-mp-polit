package com.autodag.service;

import com.autodag.dto.request.CreateDagRequest;
import com.autodag.dto.response.DagResponse;
import java.util.List;

public interface DagTaskService {
    
    DagResponse createDag(CreateDagRequest request);
    
    List<DagResponse> getAllDags();
    
    DagResponse getDagById(Long id);
    
    DagResponse updateDag(Long id, CreateDagRequest request);
    
    void deleteDag(Long id);
}