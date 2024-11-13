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
@Table(name = "compensation")
public class Compensation {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id",columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
