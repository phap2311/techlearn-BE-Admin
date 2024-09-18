package com.techzen.techlearn.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private Long courseId;
}
