package com.pe.claims.aplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {

    private UUID id;
    private Date registrationDate;
    private String description;
    private String status;
    private String urlFile;
    private UUID flightCustomerId;
    private String claimCode;

    //    private FlightCustomer flightCustomer; lo de abako es  lo de flight
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private Date departureTime;
    private Date arrivalTime;

    private String compensationName;
    private String compensationDescription;


}
