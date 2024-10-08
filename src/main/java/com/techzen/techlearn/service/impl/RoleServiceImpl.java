package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.response.RoleResponseDTO;
import com.techzen.techlearn.entity.Role;
import com.techzen.techlearn.repository.RoleRepository;
import com.techzen.techlearn.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    @Override
    public List<RoleResponseDTO> getAllRole() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RoleResponseDTO convertToDTO(Role role) {
        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName().name())
                .build();
    }
}
