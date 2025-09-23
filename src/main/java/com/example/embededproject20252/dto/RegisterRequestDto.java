package com.example.embededproject20252.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDto(

    @NotBlank(message = "시리얼 번호는 필수입니다.")
    String serialNum,

    @NotBlank(message = "사용자명은 필수입니다.")
    String name,

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^01[0-9]-?[0-9]{4}-?[0-9]{4}$",
        message = "올바른 전화번호 형식이 아닙니다.")
    String phoneNumber
) {
}
