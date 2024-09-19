package com.techzen.techlearn.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Order is required")
    private Integer chapter_order;

    private Boolean isPublic;

    @NotNull(message = "CourseId is required")
    private Long courseId;
}
