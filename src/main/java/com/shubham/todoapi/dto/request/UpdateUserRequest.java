package com.shubham.todoapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Email(message = "Enter valid email")
    @NotBlank(message = "email can not be blank")
    private String email;
}
