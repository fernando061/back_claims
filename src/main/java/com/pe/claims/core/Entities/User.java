package com.pe.claims.core.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "document", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String document;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "last_name_two", length = 50)
    private String lastNameTwo;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private Status status;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;
    // Define el enum Status
    public enum Status {
        ACTIVE,
        INACTIVE
    }



}
