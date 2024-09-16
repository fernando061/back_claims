package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.RegisterClaimDto;
import com.pe.claims.aplication.DTO.SearchClaimDtoRequest;
import com.pe.claims.aplication.DTO.SearchClaimDtoResponse;
import com.pe.claims.aplication.Mapper.ClaimMapper;
import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.infraestructure.Repository.FlightCustomerRepository;
import com.pe.claims.infraestructure.Service.ComplaintService;
import com.pe.claims.infraestructure.Service.CustomerService;
import com.pe.claims.infraestructure.Service.FlightCustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class ComplaintsService implements IcomplaintService{

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    FlightCustomerService flightCustomerService;

    @Autowired
    FlightCustomerRepository flightCustomerRepository;

    @Autowired
    EmailService emailService;
    @Autowired
    S3Service s3Service;
    @Override
    public String RegisterClaim(RegisterClaimDto registerClaimDto) {
        try {
            String urlFile = s3Service.UploadFile(registerClaimDto.getFile());
            String claimCode;

            Complaint complaint = claimMapper.toComplaint(registerClaimDto);
            complaint.setUrlFile(urlFile);
            FlightCustomer flightCustomer = flightCustomerRepository.findById(complaint.getFlightCustomerId()).get();

            do {
                claimCode = GenerateClaimCode();
            } while (complaintService.existsByClaimCode(claimCode));
            complaint.setClaimCode(claimCode);

            complaint.setFlightCustomer(flightCustomer);
            complaintService.ComplaintSave(complaint);
            emailService.sendEmail(registerClaimDto.getEmail(), "Estimado pasajero;","Su reclamo fue registrado exitosamente",claimCode);
            return claimCode;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public SearchClaimDtoResponse findByFlightNumberAndCustomerDocumentNumber(SearchClaimDtoRequest searchClaimDtoRequest) {
        return claimMapper.toSearchClaimDtoResponse(flightCustomerService.findByFlightNumberAndCustomerDocumentNumber(searchClaimDtoRequest.getFlightNumber(), searchClaimDtoRequest.getDocumentNumber()));
    }


    private static String GenerateClaimCode() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int LENGTH = 10;
        final SecureRandom random = new SecureRandom();

        StringBuilder code = new StringBuilder("CLM-");
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}