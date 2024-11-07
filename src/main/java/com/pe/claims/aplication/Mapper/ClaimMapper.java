package com.pe.claims.aplication.Mapper;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.aplication.Helpers.EnumStatus;
import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.core.Entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public LoginUserResponseDto toLoginUserResponseDto(Customer customer, String token){
        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto();
        CustomerDto _customer = modelMapper.map(customer, CustomerDto.class);
        _customer.setRole("user");
        loginUserResponseDto.setToken(token);
        loginUserResponseDto.setCustomer(_customer);
        return loginUserResponseDto;
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

    public ComplaintDto toComplaintDto(Complaint complaint) {
        if (complaint == null) {
            return null; // Manejo de nulos
        }

        ComplaintDto complaintDto = new ComplaintDto();

        // Mapeo manual de cada campo
        complaintDto.setId(complaint.getId());
        complaintDto.setRegistrationDate(complaint.getRegistrationDate());
        complaintDto.setDescription(complaint.getDescription());
        complaintDto.setStatus(complaint.getStatus());
        complaintDto.setUrlFile(complaint.getUrlFile());
        complaintDto.setClaimCode(complaint.getClaimCode());
        complaintDto.setFlightCustomerId(complaint.getFlightCustomerId());

        if (complaint.getFlightCustomer() != null) {
            FlightCustomer flightCustomer = complaint.getFlightCustomer();
            complaintDto.setFlightNumber(flightCustomer.getFlightNumber());
            complaintDto.setDepartureCity(flightCustomer.getDepartureCity());
            complaintDto.setArrivalCity(flightCustomer.getArrivalCity());
            complaintDto.setDepartureTime(flightCustomer.getDepartureTime());
            complaintDto.setArrivalTime(flightCustomer.getArrivalTime());
        }

        return complaintDto;
    }
    public List<ComplaintDto> toComplaintDtoList(List<Complaint> complaints) {

        return complaints.stream()
                .map(this::toComplaintDto)
                .collect(Collectors.toList());
    }

    public CustomerComplaintDtoResponse toCustomerComplaintDtoResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerComplaintDtoResponse response = new CustomerComplaintDtoResponse();

        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setPhone(customer.getPhone());
        response.setCountryCode(customer.getCountryCode());
        response.setEmail(customer.getEmail());
        response.setDocumentNumber(customer.getDocumentNumber());

        return response;
    }

    public User toUser(EmployeeRegistrationRequestDto employeDto){
        User user =new User();
        user.setDocument(employeDto.getDocument());
        user.setName(employeDto.getName());
        user.setLastName(employeDto.getLastName());
        user.setLastNameTwo(employeDto.getLastNameTwo());
        user.setEmail(employeDto.getEmail());
        user.setPhoneNumber(employeDto.getPhoneNumber());
        user.setStatus(User.Status.ACTIVE);
        return  user;
    }
}
