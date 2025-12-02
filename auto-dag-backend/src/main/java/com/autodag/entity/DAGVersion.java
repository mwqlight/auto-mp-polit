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
@Table(name = "dag_version")
public class DAGVersion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "dag_id", nullable = false)
    private Long dagId;
    
    @Column(name = "version_number", nullable = false)
    private String versionNumber;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "dag_definition", columnDefinition = "TEXT")
    private String dagDefinition;
    
    @Column(name = "validation_status")
    private String validationStatus;
}