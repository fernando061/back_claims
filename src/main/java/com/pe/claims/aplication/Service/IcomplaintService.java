package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.core.Entities.Complaint;

public interface IcomplaintService {
    RegisterClaimDtoResponse RegisterClaim(RegisterClaimDto registerClaimDto);
    SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest);
}
