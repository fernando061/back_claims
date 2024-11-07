package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.EmployeeRegistrationRequestDto;
import com.pe.claims.aplication.DTO.LoginUserRequestDto;
import com.pe.claims.aplication.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping("${path.employee.employeeRegister}")
    public ResponseEntity<Object> LoginCustomer(@RequestBody EmployeeRegistrationRequestDto employeeRegister){
        employeeService.EmployeeRegister(employeeRegister);
        return ResponseEntity.ok(null);
    }
}
