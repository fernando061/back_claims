package com.pe.claims.core.Entities;

import com.pe.claims.core.ValueObject.EmailValueObject;
import com.pe.claims.core.ValueObject.UUIDValueObject;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id",columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)

    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "countrycode")
    private String countryCode;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "document_number", nullable = false,unique = true)
    private String documentNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FlightCustomer flightCustomer;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FlightCustomer> flightCustomers;

    public Customer() {
    }
    public Customer(String id, String name, String phone, String countryCode, String email) {
        this.id = new UUIDValueObject(id).getId();
        this.name = name;
        this.phone = phone;
        this.countryCode = countryCode;
        this.email = new  EmailValueObject(email).getEmail();
    }

    // Setter
    public void setEmail(String email) {
        this.email = new EmailValueObject(email).getEmail();
    }
    // Setter
    public void setUUID(String id) {
        this.id = new UUIDValueObject(email).getId();
    }
}
