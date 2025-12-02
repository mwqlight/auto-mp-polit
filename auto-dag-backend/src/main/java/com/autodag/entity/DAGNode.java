package com.autodag.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dag_node")
public class DAGNode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "node_id", unique = true, nullable = false)
    private String nodeId;
    
    @Column(name = "version_id", nullable = false)
    private Long versionId;
    
    @Column(name = "node_name", nullable = false)
    private String nodeName;
    
    @Column(name = "node_type", nullable = false)
    private String nodeType;
    
    @Column(name = "position_x")
    private BigDecimal positionX;
    
    @Column(name = "position_y")
    private BigDecimal positionY;
    
    @Column(name = "position_z")
    private BigDecimal positionZ;
    
    @Column(name = "task_artifact_id")
    private Long taskArtifactId;
    
    @Column(name = "config_overrides", columnDefinition = "TEXT")
    private String configOverrides;
    
    @Column(name = "resource_requirements", columnDefinition = "TEXT")
    private String resourceRequirements;
    
    @Column(name = "parallelism_config", columnDefinition = "TEXT")
    private String parallelismConfig;
}