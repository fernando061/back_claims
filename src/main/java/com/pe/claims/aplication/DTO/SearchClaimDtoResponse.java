package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchClaimDtoResponse {
    private UUID id;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private Date departureTime;
    private Date arrivalTime;
    private CustomerDto customer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerDto {
        private UUID id;
        private String name;
        private String phone;
        private String countryCode;
        private String email;
        private String documentNumber;
    }
}

