package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginDtoResponse {
    private EmployeeDto employee;
    private String token;
}
