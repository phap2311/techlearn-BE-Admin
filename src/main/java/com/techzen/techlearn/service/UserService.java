package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.UserRequestDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    UserResponseDTO getUserById(UUID id);

    UserResponseDTO addUser(UserRequestDTO request);

    UserResponseDTO updateUser(UUID id, UserRequestDTO request);

    void deleteUser(UUID id);

    PageResponse<?> getAllUser(int page, int pageSize);
}
