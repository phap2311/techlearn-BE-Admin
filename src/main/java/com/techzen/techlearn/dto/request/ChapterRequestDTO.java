package com.techzen.techlearn.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterRequestDTO {

    @NotBlank(message = "CHAPTER_NAME_INVALID")
    private String name;

    @NotNull(message = "CHAPTER_ORDER_INVALID")
    private Integer chapter_order;

    @NotNull(message = "CHAPTER_IS_PUBLIC_INVALID")
    private Boolean isPublic;

    @NotNull(message = "CHAPTER_COURSE_ID_INVALID")
    private Long courseId;
}
