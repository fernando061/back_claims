package com.pe.claims.aplication.Interface;

import com.pe.claims.aplication.DTO.*;

import java.util.List;

public interface IEmployeeService {

    void EmployeeRegister(EmployeeRegistrationRequestDto employeeRegistrationRequestDto);
    EmployeeLoginDtoResponse Login(LoginDtoRequest loginDto);
    EmployeeLoginDtoResponse refreshToken(String documentNumber);
    List<CustomerComplaintDtoResponse> customerComplaintList();
    StatusMessageResponse manageClaim(ManageclaimDtoRequest manageClaim);
}
