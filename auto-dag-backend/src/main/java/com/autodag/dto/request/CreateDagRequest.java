package com.autodag.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateDagRequest {
    
    private String dagId;
    
    private String name;
    
    private String description;
    
    private String version;
    
    private String config;
    
    private List<String> dependencies;
}