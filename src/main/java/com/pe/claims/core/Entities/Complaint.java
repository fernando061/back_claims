package com.pe.claims.core.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id",columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "registration_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "url_file", columnDefinition = "TEXT")
    private String urlFile;

    @Column(name = "flight_customer_id",columnDefinition = "VARCHAR(36)", insertable = false, updatable = false,nullable = false)
    private UUID flightCustomerId;

    @Column(name = "claim_code", unique = true,nullable = false)
    private String claimCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_customer_id", nullable = false)
    private FlightCustomer flightCustomer;
}
