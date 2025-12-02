package com.autodag.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_artifact")
public class TaskArtifact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "artifact_id", unique = true, nullable = false)
    private String artifactId;
    
    @Column(name = "dag_id", nullable = false)
    private Long dagId;
    
    @Column(name = "artifact_name", nullable = false)
    private String artifactName;
    
    @Column(name = "version")
    private String version;
    
    @Column(name = "language")
    private String language;
    
    @Column(name = "source_code_repo")
    private String sourceCodeRepo;
    
    @Column(name = "dependencies", columnDefinition = "TEXT")
    private String dependencies;
    
    @Column(name = "build_artifacts", columnDefinition = "TEXT")
    private String buildArtifacts;
    
    @Column(name = "validation_results", columnDefinition = "TEXT")
    private String validationResults;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "created_by")
    private String createdBy;
}