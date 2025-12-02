package com.autodag.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class DAGRequestDTO {
    private String dagId;
    private String name;
    private String description;
    private String version;
    private ScheduleConfig schedule;
    private List<Node> nodes;
    private List<Edge> edges;
    private GlobalConfigs globalConfigs;
    private MapReduceConfig mapReduceConfig;
    
    @Data
    public static class ScheduleConfig {
        private String type;
        private String expression;
        private String timezone;
        private RetryPolicy retryPolicy;
    }
    
    @Data
    public static class RetryPolicy {
        private Integer maxRetries;
        private String retryInterval;
        private Double backoffMultiplier;
    }
    
    @Data
    public static class Node {
        private String nodeId;
        private String name;
        private String type;
        private Position position;
        private TaskDefinition taskDefinition;
        private ResourceRequirements resourceRequirements;
        private ExecutionConfig executionConfig;
    }
    
    @Data
    public static class Position {
        private Double x;
        private Double y;
        private Double z;
    }
    
    @Data
    public static class TaskDefinition {
        private String artifactId;
        private String className;
        private String runtime;
        private Object inputSchema;
        private Object outputSchema;
    }
    
    @Data
    public static class ResourceRequirements {
        private Integer cpuCores;
        private Integer memoryMB;
        private Integer diskGB;
        private Integer gpuUnits;
        private String networkBandwidth;
        private String gpuType;
    }
    
    @Data
    public static class ExecutionConfig {
        private String timeout;
        private Integer parallelism;
        private Boolean isMapStage;
        private Boolean isReduceStage;
        private Boolean memoryIntensive;
        private String partitionStrategy;
    }
    
    @Data
    public static class Edge {
        private String from;
        private String to;
        private Object dataMapping;
        private String condition;
    }
    
    @Data
    public static class GlobalConfigs {
        private String defaultResourcePool;
        private String dataRetentionPolicy;
        private List<String> notificationChannels;
        private String observabilityLevel;
    }
    
    @Data
    public static class MapReduceConfig {
        private Boolean enabled;
        private Integer mapParallelismDefault;
        private Integer reduceParallelismDefault;
        private String shuffleStrategy;
        private Boolean compressionEnabled;
        private Integer spillThresholdMB;
    }
}