package com.autodag.mapper;

import com.autodag.entity.DAG;
import com.autodag.entity.DAGVersion;
import com.autodag.dto.request.DAGRequestDTO;
import com.autodag.dto.response.DAGResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DAGMapper {
    DAGMapper INSTANCE = Mappers.getMapper(DAGMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "dagId", target = "dagId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "status", target = "status"),
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt"),
        @Mapping(source = "createdBy", target = "createdBy")
    })
    DAGResponseDTO dagToDAGResponseDTO(DAG dag);
    
    @Mappings({
        @Mapping(source = "dagId", target = "dagId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "description", target = "description"),
        @Mapping(target = "status", constant = "ACTIVE"),
        @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
        @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),
        @Mapping(target = "createdBy", constant = "system")
    })
    DAG dagRequestDTOToDAG(DAGRequestDTO dagRequestDTO);
}