package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.RegisterClaimDto;
import com.pe.claims.aplication.DTO.RegisterClaimDtoResponse;
import com.pe.claims.aplication.DTO.SearchClaimDtoRequest;
import com.pe.claims.aplication.DTO.SearchClaimDtoResponse;

public interface IcomplaintService {
    RegisterClaimDtoResponse RegisterClaim(RegisterClaimDto registerClaimDto);
    SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest);
}
