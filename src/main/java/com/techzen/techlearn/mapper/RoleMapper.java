package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.RoleDTO;
import com.techzen.techlearn.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRoleEntity(RoleDTO dto);
}
