package com.pe.claims.aplication.Interface;

import com.pe.claims.aplication.DTO.*;

public interface IcomplaintService {
    RegisterClaimDtoResponse RegisterClaim(RegisterClaimDto registerClaimDto);
    SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest);
}
