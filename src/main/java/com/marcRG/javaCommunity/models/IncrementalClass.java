package com.marcRG.javaCommunity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@Data
@MappedSuperclass
@NoArgsConstructor
@Table(name = "IncrementalClass")
@EntityListeners(AuditingEntityListener.class)
public class IncrementalClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "dateCreation",nullable = false,updatable = false)
    private Instant dateCreation;

    @LastModifiedDate
    @Column(name = "dateUpdate",nullable = false)
    private Instant dateUpdate;
}
