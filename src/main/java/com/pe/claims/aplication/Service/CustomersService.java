package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.CustomerComplaintDtoResponse;
import com.pe.claims.aplication.DTO.LoginUserRequestDto;
import com.pe.claims.aplication.DTO.LoginUserResponseDto;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Mapper.ClaimMapper;
import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.infraestructure.Repository.FlightCustomerRepository;
import com.pe.claims.infraestructure.Service.ComplaintService;
import com.pe.claims.infraestructure.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class CustomersService implements ICustomerService{

    @Autowired
    private ClaimMapper claimMapper;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    FlightCustomerRepository flightCustomerRepository;

    @Override
    public LoginUserResponseDto findByDocumentNumberAndClaimcode(LoginUserRequestDto loginClient) {

        var complaint = complaintService.findByDocumentNumberAndClaimcode(loginClient.claimCode(), loginClient.documentNumber());
        String token = "";
        if(complaint != null){
            JwtUtil jwtUtil = new JwtUtil();
            String name = complaint.getFlightCustomer().getCustomer().getName();
            String documentNumber = complaint.getFlightCustomer().getCustomer().getDocumentNumber();
            token = jwtUtil.generateToken(name,documentNumber);
            return claimMapper.toLoginUserResponseDto(complaint.getFlightCustomer().getCustomer(),token);
        }

        throw  new RuntimeException();
    }

    @Override
    public LoginUserResponseDto refreshToken(String documentNumber) {
        var customer = customerService.findByDocumentNumber(documentNumber);
        if(customer!=null){
            JwtUtil jwtUtil = new JwtUtil();
            String token = "";
            token = jwtUtil.generateToken(customer.getName(),documentNumber);
            return claimMapper.toLoginUserResponseDto(customer,token);
        }
        throw  new RuntimeException();
    }

    @Override
    public CustomerComplaintDtoResponse customerComplaint(String customerID) {
        UUID customerId = UUID.fromString(customerID);
        var customer = customerService.findById(customerId);
        var flightCustomers = flightCustomerRepository.findByCustomerId(customerId);
        var uuidsFlightCustomer = Arrays.stream(flightCustomers.stream().map(FlightCustomer::getId).toArray(UUID[]::new)).toList();
        var complainst = complaintService.findByFlightCustomerIdIn(uuidsFlightCustomer);

        CustomerComplaintDtoResponse customerComplaint = claimMapper.toCustomerComplaintDtoResponse(customer);
        var complaintDtos = claimMapper.toComplaintDtoList(complainst);

        customerComplaint.setComplaints(complaintDtos);
        return customerComplaint;

    }

}
