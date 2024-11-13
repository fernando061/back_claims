package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("${path.employee.employeeRegister}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> EmployeeRegister(@RequestBody EmployeeRegistrationRequestDto employeeRegister){
        employeeService.EmployeeRegister(employeeRegister);
        return ResponseEntity.ok(null);
    }

    @PostMapping("${path.employee.employeeLogin}")
    public ResponseEntity<EmployeeLoginDtoResponse> Login(@RequestBody LoginDtoRequest login){
        return ResponseEntity.ok(employeeService.Login(login));
    }

    @GetMapping("${path.employee.checkToken}")
    public ResponseEntity<Object> checkToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        JwtUtil jwtUtil = new JwtUtil();
        String documentNumber = jwtUtil.extractDocumentNumber(token);
        return ResponseEntity.ok(employeeService.refreshToken(documentNumber));
    }

    @GetMapping("${path.employee.complaintList}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLAIMS_ANALYST')")
    public ResponseEntity<List<CustomerComplaintDtoResponse>> CustomerList(){
        return ResponseEntity.ok(employeeService.customerComplaintList());
    }

    @PostMapping("${path.employee.manageClaim}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLAIMS_ANALYST')")
    public ResponseEntity<StatusMessageResponse> ManageClaim(@RequestBody ManageclaimDtoRequest manageClaim){
        return ResponseEntity.ok(employeeService.manageClaim(manageClaim));
    }
}
