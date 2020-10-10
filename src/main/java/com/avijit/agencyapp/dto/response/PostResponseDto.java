package com.avijit.agencyapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long id;

    private String status;

    private String location;

    private String postBy;

    private String postAt;

    private String privacyType;

}
