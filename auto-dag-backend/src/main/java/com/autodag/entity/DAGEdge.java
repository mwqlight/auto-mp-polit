package com.autodag.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dag_edge")
public class DAGEdge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "version_id", nullable = false)
    private Long versionId;
    
    @Column(name = "from_node_id", nullable = false)
    private String fromNodeId;
    
    @Column(name = "to_node_id", nullable = false)
    private String toNodeId;
    
    @Column(name = "condition")
    private String condition;
    
    @Column(name = "data_mapping", columnDefinition = "TEXT")
    private String dataMapping;
    
    @Column(name = "edge_properties", columnDefinition = "TEXT")
    private String edgeProperties;
}