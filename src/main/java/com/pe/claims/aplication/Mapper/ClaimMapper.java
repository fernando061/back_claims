package com.pe.claims.aplication.Mapper;

import com.pe.claims.aplication.DTO.RegisterClaimDto;
import com.pe.claims.aplication.DTO.SearchClaimDtoResponse;
import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.core.Entities.FlightCustomer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClaimMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Complaint toComplaint(RegisterClaimDto registerClaimDto) {

        Complaint complaint = modelMapper.map(registerClaimDto, Complaint.class);
        complaint.setUrlFile("test.jpg");
        complaint.setRegistrationDate(new Date());
        complaint.setStatus("Active");
        return complaint;
    }

    public SearchClaimDtoResponse toSearchClaimDtoResponse(FlightCustomer flightCustomer) {
        SearchClaimDtoResponse response = new SearchClaimDtoResponse();

        response.setId(flightCustomer.getId());
        response.setFlightNumber(flightCustomer.getFlightNumber());
        response.setDepartureCity(flightCustomer.getDepartureCity());
        response.setArrivalCity(flightCustomer.getArrivalCity());
        response.setDepartureTime(flightCustomer.getDepartureTime());
        response.setArrivalTime(flightCustomer.getArrivalTime());

        SearchClaimDtoResponse.CustomerDto customerDto = getCustomerDto(flightCustomer);

        response.setCustomer(customerDto);

        return response;
    }

    private static SearchClaimDtoResponse.CustomerDto getCustomerDto(FlightCustomer flightCustomer) {
        SearchClaimDtoResponse.CustomerDto customerDto = new SearchClaimDtoResponse.CustomerDto();
        customerDto.setId(flightCustomer.getCustomer().getId());
        customerDto.setName(flightCustomer.getCustomer().getName());
        customerDto.setPhone(flightCustomer.getCustomer().getPhone());
        customerDto.setCountryCode(flightCustomer.getCustomer().getCountryCode());
        customerDto.setEmail(flightCustomer.getCustomer().getEmail());
        customerDto.setDocumentNumber(flightCustomer.getCustomer().getDocumentNumber());
        return customerDto;
    }
}
