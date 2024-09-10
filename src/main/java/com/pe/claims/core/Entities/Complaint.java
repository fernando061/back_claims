package com.pe.claims.core.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "id",columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "url_file", columnDefinition = "TEXT")
    private String urlFile;

    @Column(name = "flight_customer_id", insertable = false, updatable = false)
    private UUID flightCustomerId;

    @ManyToOne
    @JoinColumn(name = "flight_customer_id", nullable = false)
    private FlightCustomer flightCustomer;
}
