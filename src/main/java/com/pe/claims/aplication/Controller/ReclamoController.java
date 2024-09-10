package com.pe.claims.aplication.Controller;

import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.infraestructure.Service.CustomerService;
import com.pe.claims.infraestructure.Service.FlightCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReclamoController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    FlightCustomerService flightCustomerService;

    @GetMapping("${path.claim.flightSearch}")
    public ResponseEntity<FlightCustomer> famousQuotes() {
        FlightCustomer rpta = flightCustomerService.findByFlightNumberAndCustomerDocumentNumber("FN001","1234567890");

        return ResponseEntity.ok(rpta);
    }
}
