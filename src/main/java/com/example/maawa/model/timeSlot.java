package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class timeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer clinicId;

    @NotNull(message = "serviceId must not be empty")
    Integer serviceId;

    @NotNull(message = "time must not be empty!")
    @Future(message = "Date must be in the future")
    LocalDateTime time;

    @NotNull
   // @Column(columnDefinition = "not null")
    Boolean isAvailable=true;
}
