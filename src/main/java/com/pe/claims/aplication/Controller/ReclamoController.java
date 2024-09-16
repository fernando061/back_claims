package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.RegisterClaimDto;
import com.pe.claims.aplication.DTO.SearchClaimDtoRequest;
import com.pe.claims.aplication.DTO.SearchClaimDtoResponse;
import com.pe.claims.aplication.Service.ComplaintsService;
import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.infraestructure.Repository.ComplaintRepository;
import com.pe.claims.infraestructure.Service.CustomerService;
import com.pe.claims.infraestructure.Service.FlightCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//AWS
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ReclamoController {



    @Autowired
    private ComplaintsService complaintsService;

    @PostMapping("${path.claim.flightSearch}")
    public ResponseEntity<SearchClaimDtoResponse> searchClaim(@RequestBody SearchClaimDtoRequest searchClaim) {
        return ResponseEntity.ok(complaintsService.findByFlightNumberAndCustomerDocumentNumber(searchClaim));
    }
    @Operation(
            summary = "Register a new claim",
            description = "Allows registering a claim along with an image",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = RegisterClaimDto.class)
                    )
            )
    )
    @PostMapping("${path.claim.registerClaim}")
    public ResponseEntity<String> registerClaims(
            @ModelAttribute RegisterClaimDto registerClaimDto
    ) {
        return ResponseEntity.ok(complaintsService.RegisterClaim(registerClaimDto));
    }

    @GetMapping("${path.claim.UUIDGenerate}")
    public ResponseEntity<String> UUIDGenerate() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
