package com.autodag.service;

import com.autodag.dto.request.DAGRequestDTO;
import com.autodag.dto.response.DAGResponseDTO;
import java.util.List;

public interface DAGService {
    DAGResponseDTO createDAG(DAGRequestDTO dagRequestDTO);
    DAGResponseDTO getDAGById(Long id);
    DAGResponseDTO getDAGByDagId(String dagId);
    List<DAGResponseDTO> getAllDAGs();
    DAGResponseDTO updateDAG(Long id, DAGRequestDTO dagRequestDTO);
    void deleteDAG(Long id);
}