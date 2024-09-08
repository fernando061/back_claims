package com.pe.claims.core.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "flight_customer")
public class FlightCustomer {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "flightCustomer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Complaint> complaints;
}
