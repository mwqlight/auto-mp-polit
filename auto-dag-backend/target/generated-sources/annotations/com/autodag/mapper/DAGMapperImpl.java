package com.autodag.mapper;

import com.autodag.dto.request.DAGRequestDTO;
import com.autodag.dto.response.DAGResponseDTO;
import com.autodag.entity.DAG;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T18:18:06+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DAGMapperImpl implements DAGMapper {

    @Override
    public DAGResponseDTO dagToDAGResponseDTO(DAG dag) {
        if ( dag == null ) {
            return null;
        }

        DAGResponseDTO dAGResponseDTO = new DAGResponseDTO();

        dAGResponseDTO.setId( dag.getId() );
        dAGResponseDTO.setDagId( dag.getDagId() );
        dAGResponseDTO.setName( dag.getName() );
        dAGResponseDTO.setDescription( dag.getDescription() );
        dAGResponseDTO.setStatus( dag.getStatus() );
        dAGResponseDTO.setCreatedAt( dag.getCreatedAt() );
        dAGResponseDTO.setUpdatedAt( dag.getUpdatedAt() );
        dAGResponseDTO.setCreatedBy( dag.getCreatedBy() );

        return dAGResponseDTO;
    }

    @Override
    public DAG dagRequestDTOToDAG(DAGRequestDTO dagRequestDTO) {
        if ( dagRequestDTO == null ) {
            return null;
        }

        DAG dAG = new DAG();

        dAG.setDagId( dagRequestDTO.getDagId() );
        dAG.setName( dagRequestDTO.getName() );
        dAG.setDescription( dagRequestDTO.getDescription() );

        dAG.setStatus( "ACTIVE" );
        dAG.setCreatedAt( java.time.LocalDateTime.now() );
        dAG.setUpdatedAt( java.time.LocalDateTime.now() );
        dAG.setCreatedBy( "system" );

        return dAG;
    }
}
