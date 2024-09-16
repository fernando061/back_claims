package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Interfaces.IComplaintService;
import com.pe.claims.core.Interfaces.ICustomerService;
import com.pe.claims.infraestructure.Repository.ComplaintRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ComplaintService extends  GenericService<Complaint> implements IComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;

    @Transactional
    @Override
    public void ComplaintSave(Complaint complaint) {
        try {

            complaintRepository.save(complaint);
        } catch (Exception e) {
            System.err.println("Error saving complaint: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean existsByClaimCode(String claimCode) {
        return  complaintRepository.existsByClaimCode(claimCode);
    }


}
