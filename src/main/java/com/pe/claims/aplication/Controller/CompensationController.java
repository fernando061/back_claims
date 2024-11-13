package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.CustomerComplaintDtoResponse;
import com.pe.claims.aplication.Service.CompensationsService;
import com.pe.claims.core.Entities.Compensation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompensationController {

    @Autowired
    CompensationsService compensationService;

    @GetMapping("${path.compensation.list}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLAIMS_ANALYST')")
    public ResponseEntity<List<Compensation>> compensationList(){
        return ResponseEntity.ok(compensationService.findAll());
    }
}
