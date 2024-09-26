package com.techzen.techlearn.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentorRequestDTO {

    UUID id;

    String name;

    String avatar;

    String color;

    String email;

}
