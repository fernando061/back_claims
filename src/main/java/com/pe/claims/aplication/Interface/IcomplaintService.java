package com.pe.claims.aplication.Interface;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.core.Entities.Complaint;

import java.util.UUID;

public interface IcomplaintService {
    RegisterClaimDtoResponse RegisterClaim(RegisterClaimDto registerClaimDto);
    SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest);
    ComplaintDtoResponse findById(UUID id);
}
