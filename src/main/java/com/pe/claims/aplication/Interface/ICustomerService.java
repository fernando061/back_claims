package com.pe.claims.aplication.Interface;

import com.pe.claims.aplication.DTO.CustomerComplaintDtoResponse;
import com.pe.claims.aplication.DTO.LoginUserRequestDto;
import com.pe.claims.aplication.DTO.LoginUserResponseDto;

public interface ICustomerService {
    LoginUserResponseDto findByDocumentNumberAndClaimcode(LoginUserRequestDto loginClient);
    LoginUserResponseDto refreshToken(String documentNumber);
    CustomerComplaintDtoResponse customerComplaint(String documentNumber);
}
