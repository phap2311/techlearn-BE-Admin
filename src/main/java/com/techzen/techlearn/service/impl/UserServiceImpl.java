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
import com.techzen.techlearn.service.ImageService;
import com.techzen.techlearn.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    ImageService imageService;
    @Override
    public UserResponseDTO2 getUserById(UUID id) {
        UserEntity user = userRepository.findUserById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponseDTO2(user);
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
        List<UserResponseDTO2> list = users.map(userMapper::toUserResponseDTO2).stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public UserResponseDTO2 createUser(UserRequestDTO2 request, MultipartFile multipartFile) {
        UserEntity user = userMapper.toUserDTO2Entity(request);

            user.setAvatar(imageService.upload(multipartFile));

        List<RoleType> roles = request.getRoles();
        if (roles != null && !roles.isEmpty()) {
            mapRolesToUser(user, roles);
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


    @Override
    public UserResponseDTO2 updateUserDTO(UUID id, UserRequestDTO2 request) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        var userMap = userMapper.toUserDTO2Entity(request);
        userMap.setId(id);
        userMap.setIsDeleted(false);

        List<RoleType> roles = request.getRoles();
        if (roles != null && !roles.isEmpty()) {
            mapRolesToUser(userMap, roles);

            if (roles.contains(RoleType.TEACHER)) {
                MentorEntity mentorEntity = mentorRepository.findMentorEntityById(userMap.getId());
                if (mentorEntity != null) {
                    mentorEntity.setIsDeleted(true);
//                    mentorRepository.delete(mentorEntity);
                    mentorRepository.save(mentorEntity);
                }

                TeacherEntity teacherEntity = teacherMapper.toTeacherEntity(userMap);
                teacherEntity.setIsDeleted(false);
                teacherRepository.save(teacherEntity);

            } else if (roles.contains(RoleType.MENTOR)) {
                TeacherEntity teacherEntity = teacherRepository.findTeacherEntityById(userMap.getId());
                if (teacherEntity != null) {
                    teacherEntity.setIsDeleted(true);
//                    teacherRepository.delete(teacherEntity);
                    teacherRepository.save(teacherEntity);
                }

                MentorEntity mentorEntity = mentorMapper.toMentorEntity(userMap);
                mentorEntity.setIsDeleted(false);
                mentorRepository.save(mentorEntity);

            } else {
                MentorEntity mentorEntity = mentorRepository.findMentorEntityById(userMap.getId());
                if (mentorEntity != null && !roles.contains(RoleType.MENTOR)) {
                    mentorEntity.setIsDeleted(true);
//                    mentorRepository.delete(mentorEntity);
                    mentorRepository.save(mentorEntity);
                }

                TeacherEntity teacherEntity = teacherRepository.findByUser(existingUser);
                if (teacherEntity != null && !roles.contains(RoleType.TEACHER)) {
                    teacherEntity.setIsDeleted(true);
//                    teacherRepository.delete(teacherEntity);
                    teacherRepository.save(teacherEntity);
                }
            }

        } else {
            userMap.setRoles(existingUser.getRoles());

            if (existingUser.getRoles().stream().anyMatch(role -> role.getName() == RoleType.MENTOR)) {
                MentorEntity mentorEntity = mentorRepository.findMentorEntityById(userMap.getId());
                if (mentorEntity != null) {
                    mentorEntity = mentorMapper.toMentorEntity(userMap);
                    mentorEntity.setIsDeleted(false);
                    mentorRepository.save(mentorEntity);
                }
            } else if (existingUser.getRoles().stream().anyMatch(role -> role.getName() == RoleType.TEACHER)) {
                TeacherEntity teacherEntity = teacherRepository.findTeacherEntityById(userMap.getId());
                if (teacherEntity != null) {
                    teacherEntity = teacherMapper.toTeacherEntity(userMap);
                    teacherEntity.setIsDeleted(false);
                    teacherRepository.save(teacherEntity);
                }
            }
        }

        return userMapper.toUserResponseDTO2(userRepository.save(userMap));
    }

    @Override
    public void deleteUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        user.setIsDeleted(true);

        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleType.MENTOR))) {
            MentorEntity mentor = mentorRepository.findMentorEntityById(user.getId());
            if (mentor != null) {
                mentor.setIsDeleted(true);
                mentorRepository.save(mentor);
            }
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleType.TEACHER))){
            TeacherEntity teacher = teacherRepository.findTeacherEntityById(user.getId());
            if (teacher != null){
                teacher.setIsDeleted(true);
                teacherRepository.save(teacher);
            }
        }
        userRepository.save(user);
    }


    private void mapRolesToUser(UserEntity userMap, List<RoleType> roles) {
        Set<Role> roleSet = new LinkedHashSet<>();
        for (RoleType roleType : roles) {
            Role role = roleRepository.findByName(roleType)
                    .orElseThrow(() -> new ApiException(ErrorCode.ROLE_NOT_FOUND));
            roleSet.add(role);
        }
        userMap.setRoles(roleSet);
    }


}
