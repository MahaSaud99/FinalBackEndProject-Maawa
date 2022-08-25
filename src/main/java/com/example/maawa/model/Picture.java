package com.example.maawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @URL(message = "Url only!")
    //Try unique
    @Column(columnDefinition = "varchar(255) unique")
    String pictureUlr;

    @NotNull(message = "ProductId must not be null!")
    @Column(columnDefinition = "Integer not null")
    Integer productId;
}
