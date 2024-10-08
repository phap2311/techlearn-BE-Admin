package com.techzen.techlearn.dto.request;

import com.techzen.techlearn.dto.RoleDTO;
import com.techzen.techlearn.entity.Role;
import com.techzen.techlearn.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO2 {

    @NotBlank(message = "FULL_NAME_INVALID")
    String fullName;

    @NotBlank(message = "EMAIL_INVALID")
    String email;

    String password;

    String avatar;

    List<RoleType> roles;

}
