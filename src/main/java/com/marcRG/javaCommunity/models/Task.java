package com.marcRG.javaCommunity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Task extends IncrementalClass{
    @Column(name = "descriptionTask",nullable = false)
    private String description;
    @Column(name = "dateTask",nullable = false)
    private Instant dateTask;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
}
