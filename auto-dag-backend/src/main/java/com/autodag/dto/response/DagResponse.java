package com.autodag.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DagResponse {
    
    private Long id;
    
    private String dagId;
    
    private String name;
    
    private String description;
    
    private String currentVersion;
    
    private String status;
    
    private String config;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}