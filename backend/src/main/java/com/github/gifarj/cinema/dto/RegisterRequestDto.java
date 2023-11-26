package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotEmpty(message = "should be not empty")
    @Size(min = 3, max = 40, message = "should contain minimum 3 characters, maximum 40 characters")
    private String firstname;

    @NotEmpty(message = "should be not empty")
    @Size(min = 3, max = 40, message = "should contain minimum 3 characters, maximum 40 characters")
    private String lastname;

    @NotEmpty(message = "should be not empty")
    @Size(min = 5, max = 200, message = "should contain minimum 5 characters, maximum 200 characters")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Invalid email format")
    private String email;

    @NotEmpty(message = "should be not empty")
    @Size(min = 8, message = "Password should contain Minimum eight characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must contain at least one letter and one number:")
    private String password;
}
