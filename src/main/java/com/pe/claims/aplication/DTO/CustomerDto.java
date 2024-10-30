package com.pe.claims.aplication.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
public class CustomerDto {

    private UUID id;
    private String name;
    private String phone;
    private String countryCode;
    private String email;
    private String documentNumber;
    private String role;

}
