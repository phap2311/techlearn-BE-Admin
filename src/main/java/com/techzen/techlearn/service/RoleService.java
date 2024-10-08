package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.response.RoleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleResponseDTO> getAllRole();
}
