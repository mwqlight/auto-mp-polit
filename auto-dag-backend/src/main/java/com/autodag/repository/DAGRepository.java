package com.autodag.repository;

import com.autodag.entity.DAG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DAGRepository extends JpaRepository<DAG, Long> {
    Optional<DAG> findByDagId(String dagId);
}