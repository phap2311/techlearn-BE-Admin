package com.techzen.techlearn.dto.response;

import com.techzen.techlearn.dto.RoleDTO;
import com.techzen.techlearn.entity.Role;
import com.techzen.techlearn.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO2 implements Serializable {

    UUID id;

    String fullName;

    String email;

    String avatar;

    List<Role> roles;
}
