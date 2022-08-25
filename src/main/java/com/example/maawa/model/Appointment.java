package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer userId;

    @NotNull(message = "Clinic id must not be null!")
    Integer clinicId;

    @NotNull(message = "Service id must not be null!")
    Integer serviceId;

    @NotNull(message = "Time slot id must not be null!")
    Integer timeSlotId;

    @Pattern(regexp = "(inProgress|complete)",message = "Status must be in inProgress or complete only")
    @Column(columnDefinition = "varchar(25) not null check (status='inProgress' or status='complete')")
    String status="inProgress";

    LocalDateTime dateReceived;

    @Positive(message = "total price must be positive number!")
    @Column(columnDefinition = "Double not null ")
    Double totalPrice;

    //@Column(columnDefinition = "not null")
    @NotNull
    Boolean isPrepaid=false;

}
