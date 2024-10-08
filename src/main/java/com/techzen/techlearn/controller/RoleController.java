package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.response.RoleResponseDTO;
import com.techzen.techlearn.service.RoleService;
import com.techzen.techlearn.util.JsonResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/roles")
public class RoleController {
    RoleService roleService;
    @GetMapping
    public ResponseEntity<?> getAllRole() {
        List<RoleResponseDTO> roles = (List<RoleResponseDTO>) roleService.getAllRole();
        return ResponseEntity.ok(roles);
    }
}
