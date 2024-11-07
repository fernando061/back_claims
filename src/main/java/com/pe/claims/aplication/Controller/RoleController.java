package com.pe.claims.aplication.Controller;

import com.pe.claims.aplication.DTO.CustomerComplaintDtoResponse;
import com.pe.claims.aplication.Service.RolesService;
import com.pe.claims.core.Entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    RolesService rolesService;

    @GetMapping("${path.role.findAll}")
    public ResponseEntity<List<Role>> findAll(){
        return  ResponseEntity.ok(rolesService.findAll());
    }
}
