package com.autodag.repository;

import com.autodag.entity.DagTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DagTaskRepository extends JpaRepository<DagTask, Long> {
    
    Optional<DagTask> findByDagId(String dagId);
    
    boolean existsByDagId(String dagId);
}