package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotEmpty(message = "should be not empty")
    @Size(min = 3, max = 40, message = "should contain minimum 3 characters, maximum 40 characters")
    private String name;

    @NotEmpty(message = "should be not empty")
    @Size(min = 5, max = 150, message = "should contain minimum 5 characters, maximum 150 characters")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Invalid email format")
    private String email;

    @NotEmpty(message = "should be not empty")
    @Size(min = 8, message = "Password should contain Minimum eight characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must contain at least one letter and one number:")
    private String password;
}
