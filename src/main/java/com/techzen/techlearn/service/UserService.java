package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.UserRequestDTO;
import com.techzen.techlearn.dto.request.UserRequestDTO2;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.UserResponseDTO;
import com.techzen.techlearn.dto.response.UserResponseDTO2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    UserResponseDTO2 getUserById(UUID id);

    UserResponseDTO addUser(UserRequestDTO request);

    UserResponseDTO updateUser(UUID id, UserRequestDTO request);

    void deleteUser(UUID id);

    PageResponse<?> getAllUser(int page, int pageSize);

    UserResponseDTO2 createUser(UserRequestDTO2 request);

    UserResponseDTO2 updateUserDTO(UUID id, UserRequestDTO2 request);

    void deleteUserById(UUID id);
}
