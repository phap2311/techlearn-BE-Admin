package com.techzen.techlearn.dto.request;

import com.techzen.techlearn.entity.MentorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterRequestDTO {

    @NotBlank(message = "CHAPTER_NAME_INVALID")
    String name;
    @NotBlank(message = "CHAPTER_IS_PUBLIC_INVALID")
    String isPublic;
    @NotBlank(message = "CHAPTER_COURSE_ID_INVALID")
    String courseId;
    @NotNull(message = "CHAPTER_MENTOR_INVALID")
    List<MentorEntity> mentor;

}
