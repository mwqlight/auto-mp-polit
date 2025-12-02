package com.autodag.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DAGResponseDTO {
    private Long id;
    private String dagId;
    private String name;
    private String description;
    private String currentVersion;
    private String status;
    private ScheduleInfo scheduleInfo;
    private ValidationResults validationResults;
    private ResourceEstimates resourceEstimates;
    private DataFlowAnalysis dataFlowAnalysis;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    
    @Data
    public static class ScheduleInfo {
        private String type;
        private String expression;
        private String timezone;
        private String nextRunTime;
    }
    
    @Data
    public static class ValidationResults {
        private Boolean isValid;
        private List<String> errors;
        private List<String> warnings;
        private List<DependencyCheck> dependencyChecks;
    }
    
    @Data
    public static class DependencyCheck {
        private String artifactId;
        private String status;
        private String message;
    }
    
    @Data
    public static class ResourceEstimates {
        private Integer totalCpuCores;
        private Integer totalMemoryMB;
        private Integer totalDiskGB;
        private String estimatedRuntime;
        private String resourcePool;
    }
    
    @Data
    public static class DataFlowAnalysis {
        private Integer totalNodes;
        private Integer totalEdges;
        private List<NodeMetrics> nodeMetrics;
        private List<CriticalPath> criticalPaths;
    }
    
    @Data
    public static class NodeMetrics {
        private String nodeId;
        private String nodeName;
        private String type;
        private Integer inDegree;
        private Integer outDegree;
        private String estimatedRuntime;
        private String resourceProfile;
    }
    
    @Data
    public static class CriticalPath {
        private List<String> nodeSequence;
        private String totalTime;
        private String bottleneckNodes;
    }
}