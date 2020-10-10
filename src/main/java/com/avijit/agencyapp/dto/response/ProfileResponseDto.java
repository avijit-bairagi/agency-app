package com.avijit.agencyapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

    private String fullName;
    private String email;
    private String pinStatus;
    private Long pinPostId;
}
