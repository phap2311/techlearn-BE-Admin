package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.UserRequestDTO;
import com.techzen.techlearn.dto.request.UserRequestDTO2;
import com.techzen.techlearn.dto.response.UserResponseDTO;
import com.techzen.techlearn.dto.response.UserResponseDTO2;
import com.techzen.techlearn.entity.Role;
import com.techzen.techlearn.entity.UserEntity;
import com.techzen.techlearn.enums.RoleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDTO(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleTypesToRoles")
    UserEntity toUserDTO2Entity(UserRequestDTO2 userRequestDTO);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleTypes")
    UserResponseDTO2 toUserResponseDTO2(UserEntity userEntity);

    @Named("roleTypesToRoles")
    default Set<Role> roleTypesToRoles(List<RoleType> roleTypes) {
        if (roleTypes == null) {
            return null;
        }
        Set<Role> roles = new HashSet<>();
        for (RoleType roleType : roleTypes) {
            roles.add(Role.builder().name(roleType).build());
        }
        return roles;
    }

    @Named("rolesToRoleTypes")
    default List<RoleType> rolesToRoleTypes(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }



}
