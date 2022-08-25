package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer userId;

    @NotNull(message = "Store id must not be null!")
    Integer storeId;

    @NotNull(message = "Product id must not be null!")
    Integer productId;

    @Positive(message = "Quantity must be positive number!")
    @NotNull(message = "Quantity must not be null!")
    @Column(columnDefinition = "int not null")
    Integer quantity;

    Integer orderId;
}
