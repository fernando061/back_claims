package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Service.ComplaintsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClaimController {

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
    public ResponseEntity<RegisterClaimDtoResponse> registerClaims(
            @ModelAttribute RegisterClaimDto registerClaimDto
    ) {
        return ResponseEntity.ok(complaintsService.RegisterClaim(registerClaimDto));
    }

    @GetMapping("${path.claim}/{complaintId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLAIMS_ANALYST','ROLE_CUSTOMER')")
    public ResponseEntity<ComplaintDtoResponse> findById(@PathVariable UUID complaintId) {
        return ResponseEntity.ok(complaintsService.findById(complaintId));
    }

    @GetMapping("${path.claim.UUIDGenerate}")
    public ResponseEntity<String> UUIDGenerate() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

}
