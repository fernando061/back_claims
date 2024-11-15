package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.*;
import com.pe.claims.aplication.Helpers.JwtUtil;
import com.pe.claims.aplication.Interface.IEmployeeService;
import com.pe.claims.aplication.Mapper.ClaimMapper;
import com.pe.claims.core.Entities.*;
import com.pe.claims.infraestructure.Service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PasswordService passwordService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private CompensationService compensationService;

    @Autowired
    EmailService emailService;

    @Transactional
    @Override
    public void EmployeeRegister(EmployeeRegistrationRequestDto employeeRegistrationRequestDto) {

        User user = claimMapper.toUser(employeeRegistrationRequestDto);
        String hashedPassword = passwordService.hashPassword(employeeRegistrationRequestDto.getPassword());
        user.setPassword(hashedPassword);

        List<UUID> roleIds = employeeRegistrationRequestDto.getRoleIds();
        List<UserRole> userRoles = new ArrayList<>();
        for (UUID roleId : roleIds) {
            Role role = roleService.findById(roleId);
            UserRole userRole = new UserRole();
            userRole.setId(UUID.randomUUID());
            userRole.setUser(user);
            userRole.setRole(role);
            userRoles.add(userRole);
        }

        userService.save(user);
        userRoleService.saveAll(userRoles);
    }

    @Override
    public EmployeeLoginDtoResponse Login(LoginDtoRequest loginDto) {
        EmployeeLoginDtoResponse employeeResponse = new EmployeeLoginDtoResponse();
        User user = userService.findByEmail(loginDto.getEmail());
        if(user == null) throw  new RuntimeException("email or password incorrect");

        Boolean isPassword = passwordService.matches(loginDto.getPassword(), user.getPassword());
        if(!isPassword) throw  new RuntimeException("Error password");
        var employeeDto = claimMapper.toEmployeeLoginDtoResponse(user);
        var roles = claimMapper.toRoleDtos(user.getUserRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList()));

        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(user.getName(),user.getDocument());

        employeeDto.setRole(roles);
        employeeResponse.setEmployee(employeeDto);
        employeeResponse.setToken(token);
        return employeeResponse;
    }

    @Override
    public EmployeeLoginDtoResponse refreshToken(String documentNumber) {

        JwtUtil jwtUtil = new JwtUtil();
        EmployeeLoginDtoResponse employeeResponse = new EmployeeLoginDtoResponse();

        var user = userService.findByDocument(documentNumber);

        if(user == null) throw  new RuntimeException("email or password incorrect");

        var employeeDto = claimMapper.toEmployeeLoginDtoResponse(user);
        var roles = claimMapper.toRoleDtos(user.getUserRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList()));

        String token = jwtUtil.generateToken(user.getName(),documentNumber);
        employeeDto.setRole(roles);
        employeeResponse.setEmployee(employeeDto);
        employeeResponse.setToken(token);

        return employeeResponse;

    }
    @Override
    public List<CustomerComplaintDtoResponse> customerComplaintList() {
        var customers = customerService.findAllWhereComplaintExist().stream()
                .filter(customer -> customer.getFlightCustomers() != null &&
                        customer.getFlightCustomers().stream()
                                .anyMatch(flightCustomer -> flightCustomer.getComplaints() != null &&
                                        !flightCustomer.getComplaints().isEmpty()))
                .collect(Collectors.toList());
        return claimMapper.toCustomerComplaintDtoResponseList(customers);
    }

    @Override
    public StatusMessageResponse manageClaim(ManageclaimDtoRequest manageClaim) {
        try{
            StatusMessageResponse statusMessage = new StatusMessageResponse();
            var complaint = complaintService.findById(manageClaim.getId());


            complaint.setStatus(manageClaim.getStatus());
            if(manageClaim.getStatus().equals("RESOLVED")){
                var compensation = compensationService.findById(manageClaim.getCompensationId());
                complaint.setCompensationName(compensation.getName());
                complaint.setCompensationDescription(compensation.getDescription());
                emailService.sendEmail(manageClaim.getEmail(), "Estimado pasajero;",
                        "Su reclamo fue aprovado con la compensacion: "+compensation.getName(),complaint.getClaimCode());
            }

            complaintService.ComplaintSave(complaint);
            statusMessage.setMessage("Proceso registrado exitosamente");
            statusMessage.setStatus(true);
            return statusMessage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
