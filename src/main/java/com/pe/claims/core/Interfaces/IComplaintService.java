package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Complaint;

import java.util.List;
import java.util.UUID;

public interface IComplaintService extends IGenericRepository<Complaint>{
    void ComplaintSave(Complaint complaint);
    Boolean existsByClaimCode(String claimCode);
    Complaint findByDocumentNumberAndClaimcode(String claimCode, String documentNumber);
    List<Complaint> findByFlightCustomerIdIn(List<UUID> ids);
    Complaint findById(UUID id);
}

