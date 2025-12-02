package com.autodag.repository;

import com.autodag.entity.DAGVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DAGVersionRepository extends JpaRepository<DAGVersion, Long> {
    List<DAGVersion> findByDagId(Long dagId);
}