package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserResponseDto {

    private String token;
    private CustomerDto customer;

}
