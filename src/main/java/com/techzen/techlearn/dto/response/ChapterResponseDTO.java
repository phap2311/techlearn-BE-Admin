package com.techzen.techlearn.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterResponseDTO {

    private Long id;
    private String name;
    private Long courseId;
}
