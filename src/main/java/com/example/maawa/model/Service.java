package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer clinicId;

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 3,max = 25 , message = "Name have to be 3-25 character long!")
    @Column(columnDefinition = "varchar(25) not null")
    String name;

    @NotEmpty(message = "Description must not be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    String description;

    @NotNull(message = "Price must not be null!")
    @Positive(message = "Price must be positive number!")
    @Column(columnDefinition = "Double not null")
    Double price;

    @URL(message = "Url only!")
    private String picture;
}
