package com.autodag.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dag")
public class DAG {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "dag_id", unique = true, nullable = false)
    private String dagId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "owner_id")
    private String ownerId;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "last_modified")
    private LocalDateTime lastModified;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "schedule_config")
    private String scheduleConfig;
    
    @ElementCollection
    @CollectionTable(name = "dag_tags", joinColumns = @JoinColumn(name = "dag_id"))
    @Column(name = "tag")
    private List<String> tags;
}