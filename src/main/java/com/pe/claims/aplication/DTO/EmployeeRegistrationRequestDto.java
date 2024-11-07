package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegistrationRequestDto {

    private String document;
    private String name;
    private String lastName;
    private String lastNameTwo;
    private String email;
    private String password;
    private String phoneNumber;
    private String status;
    private List<UUID> roleIds;
}
