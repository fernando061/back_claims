package com.pe.claims.aplication.DTO;

import com.pe.claims.core.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String document;
    private String name;
    private String lastName;
    private String lastNameTwo;
    private String email;
    private String phoneNumber;
    private User.Status status;
    private List<RoleDto> role;
}
