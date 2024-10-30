package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.CustomerComplaintDtoResponse;
import com.pe.claims.aplication.DTO.LoginUserRequestDto;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Service.CustomersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomersService customersService;

    @PostMapping("${path.customer.loginCustomer}")
    public ResponseEntity<Object> LoginCustomer(@RequestBody LoginUserRequestDto loginClient){
        return ResponseEntity.ok(customersService.findByDocumentNumberAndClaimcode(loginClient));
    }

    @GetMapping("${path.customer.checkToken}")
    public ResponseEntity<Object> checkToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        JwtUtil jwtUtil = new JwtUtil();
        String documentNumber = jwtUtil.extractDocumentNumber(token);
        return ResponseEntity.ok(customersService.refreshToken(documentNumber));
    }
    @GetMapping("${path.customer.customerComplaint}/{customerId}")
    public ResponseEntity<CustomerComplaintDtoResponse> CustomerComplaint(@PathVariable String customerId){
        return  ResponseEntity.ok(customersService.customerComplaint(customerId));
    }
}
