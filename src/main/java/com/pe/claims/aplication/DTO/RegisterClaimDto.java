package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClaimDto {
    private UUID id;
    private UUID flightCustomerId;
    private String name;
    private String cellphone;
    private String email;
    private String description;
    private MultipartFile file;
    private UUID customerId;
}
