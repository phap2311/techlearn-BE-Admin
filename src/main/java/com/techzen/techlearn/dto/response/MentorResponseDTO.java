package com.techzen.techlearn.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponseDTO {

    UUID id;

    String name;

    String avatar;

    String color;

    String email;
}
