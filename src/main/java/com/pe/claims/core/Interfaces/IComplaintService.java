package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Complaint;

public interface IComplaintService extends IGenericRepository<Complaint>{
    void ComplaintSave(Complaint complaint);
    Boolean existsByClaimCode(String claimCode);
}

