package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer storeId;

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 3, message = "Name have to be 3 character long at least!")
    @Column(columnDefinition = "varchar(25) unique not null")
    String name;

}
