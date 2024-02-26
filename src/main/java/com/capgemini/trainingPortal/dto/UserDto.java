package com.capgemini.trainingPortal.dto;

import org.hibernate.validator.constraints.Normalized;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private Long id;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @NotNull
    private String grade;

}
