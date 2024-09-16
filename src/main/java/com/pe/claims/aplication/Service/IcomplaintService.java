package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.RegisterClaimDto;
import com.pe.claims.aplication.DTO.SearchClaimDtoRequest;
import com.pe.claims.aplication.DTO.SearchClaimDtoResponse;

public interface IcomplaintService {
    String RegisterClaim(RegisterClaimDto registerClaimDto);
    SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest);
}
