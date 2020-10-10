package com.avijit.agencyapp.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostUpdateRequestDto {

    @NotNull
    private Long id;

    @Size(min = 10, max = 200, message = "Status must be between 10 and 100 characters.")
    private String status;

    @NotBlank
    private String privacyType;

    @NotBlank
    private String locationId;

    public PostUpdateRequestDto(Long id, @NotBlank String status) {
        this.id = id;
        this.status = status;
    }
}
