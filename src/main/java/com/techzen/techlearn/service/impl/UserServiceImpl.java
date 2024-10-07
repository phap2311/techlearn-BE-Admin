package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.UserRequestDTO;
import com.techzen.techlearn.dto.request.UserRequestDTO2;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.UserResponseDTO;
import com.techzen.techlearn.dto.response.UserResponseDTO2;
import com.techzen.techlearn.entity.MentorEntity;
import com.techzen.techlearn.entity.Role;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.entity.UserEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.enums.RoleType;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.MentorMapper;
import com.techzen.techlearn.mapper.TeacherMapper;
import com.techzen.techlearn.mapper.UserMapper;
import com.techzen.techlearn.repository.MentorRepository;
import com.techzen.techlearn.repository.RoleRepository;
import com.techzen.techlearn.repository.TeacherRepository;
import com.techzen.techlearn.repository.UserRepository;
import com.techzen.techlearn.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    TeacherRepository teacherRepository;
    MentorRepository mentorRepository;
    TeacherMapper teacherMapper;
    MentorMapper mentorMapper;
    RoleRepository roleRepository;


    @Override
    public UserResponseDTO getUserById(UUID id) {
        UserEntity user = userRepository.findUserById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO request) {
        UserEntity user = userMapper.toUserEntity(request);
        user.setIsDeleted(false);
        user = userRepository.save(user);
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UserRequestDTO request) {
        userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        var userMap = userMapper.toUserEntity(request);
        userMap.setId(id);
        userMap.setIsDeleted(false);
        return userMapper.toUserResponseDTO(userRepository.save(userMap));
    }

    @Override
    public void deleteUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public PageResponse<?> getAllUser(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<UserEntity> users = userRepository.findAll(pageable);
        List<UserResponseDTO> list = users.map(userMapper::toUserResponseDTO).stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public UserResponseDTO2 createUser(UserRequestDTO2 request) {
        UserEntity user = userMapper.toUserDTO2Entity(request);

        List<RoleType> roles = request.getRoles();
        if (roles != null && !roles.isEmpty()) {
            Set<Role> roleSet = new LinkedHashSet<>();
            for (RoleType roleType : roles) {
                Role role = roleRepository.findByName(roleType)
                        .orElseThrow(() -> new ApiException(ErrorCode.ROLE_NOT_FOUND));
                roleSet.add(role);
            }
            user.setRoles(roleSet);
        }

        user.setIsDeleted(false);
        user = userRepository.save(user);

        for (Role role : user.getRoles()) {
            if (role.getName() == RoleType.TEACHER) {
                TeacherEntity teacher = teacherMapper.toTeacherEntity(user);
                teacher.setIsDeleted(false);
                teacherRepository.save(teacher);
            } else if (role.getName() == RoleType.MENTOR) {
                MentorEntity mentor = mentorMapper.toMentorEntity(user);
                mentor.setIsDeleted(false);
                mentorRepository.save(mentor);
            }
        }

        return userMapper.toUserResponseDTO2(user);
    }


//
//    @Override
//    public UserResponseDTO2 updateUserDTO(UUID id, UserRequestDTO2 request) {
//        userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
//        var userMap = userMapper.toUserDTO2Entity(request);
//        userMap.setId(id);
//        userMap.setIsDeleted(false);
//        return userMapper.toUserResponseDTO2(userRepository.save(userMap));
//    }
}
