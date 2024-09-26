package com.techzen.techlearn.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorChapterRequestDTO {
    @NonNull
    private String mentorId;

    @NonNull
    private String chapterId;
}
