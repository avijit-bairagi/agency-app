package com.avijit.agencyapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    @Size(min = 10, max = 200, message = "Status must be between 10 and 100 characters.")
    private String status;

    @NotBlank(message = "Please select a privacy type.")
    private String privacyType;

    @NotBlank(message = "Please select a location.")
    private String locationId;
}
