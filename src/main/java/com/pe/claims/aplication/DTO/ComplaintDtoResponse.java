package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDtoResponse {
    private UUID id;
    private String name;
    private String phone;
    private String countryCode;
    private String email;
    private String documentNumber;
    private ComplaintDto Complaint ;
}
