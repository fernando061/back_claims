package com.pe.claims.core.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_customer")
public class FlightCustomer {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id",columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "flightCustomer", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Complaint> complaints = new ConcurrentSkipListSet<Complaint>();

}
