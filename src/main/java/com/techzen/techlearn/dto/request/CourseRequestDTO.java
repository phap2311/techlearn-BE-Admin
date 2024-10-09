package com.techzen.techlearn.dto.request;

import com.techzen.techlearn.entity.TeacherEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequestDTO {
    @NotBlank(message = "COURSE_NAME_INVALID")
    String name;
    @NotBlank(message = "COURSE_PRICE_INVALID")
    String price;
    @NotBlank(message = "COURSE_PRIVATE_POINT_INVALID")
    String privatePoint;
    @NotBlank(message = "COURSE_PUBLIC_POINT_INVALID")
    String publicPoint;
    @NotBlank(message = "COURSE_DESCRIPTION_INVALID")
    String description;
    @NotBlank(message = "COURSE_UNIT_INVALID")
    @Pattern(regexp = "VND|USD", message = "COURSE_CURRENCY_UNIT_INVALID")
    String currencyUnit;
    @NotBlank(message = "COURSE_IS_ACTIVE_INVALID")
    @Pattern(regexp = "true|false", message = "COURSE_IS_ACTIVE_INVALID_TYPE")
    String isActive;
    @NotNull(message = "COURSE_TECHSTACK_INVALID")
    List<String> techStack;
    @NotNull(message = "TEACHER_INVALID")
    List<UUID> teacher;
}
