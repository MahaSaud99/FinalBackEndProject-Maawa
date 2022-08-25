package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class myOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer userId;

    @NotNull(message = "Store id must not be null!")
    Integer storeId;

   // @NotEmpty(message = "Status must not be empty!")
    //@Pattern(regexp = "(new|inProgress|complete)",message = "Status must be in new or inProgress or complete only")
    @Column(columnDefinition = "varchar(10) check (status='new' or status='inProgress' or status='complete')")
    String status="new";

    //@Column(columnDefinition = "not null")
    LocalDateTime dateReceived;

    @PositiveOrZero(message = "total price must be positive number!")
   // @NotNull(message = "Total price must not be null!")
    @Column(columnDefinition = "Double not null")
    Double totalPrice;

   // @Column(columnDefinition = "not null")
   //@NotNull
   Boolean isPrepaid=false;
}
