package com.example.maawa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username must not be empty!")
    @Size(min = 5, message = "Username have to be 5 character long at least!")
    @Column(columnDefinition = "varchar(25) unique not null")
    private String username;

    @NotEmpty (message = "Password must not be empty!")
    @Size(min = 6, message = "Password have to be 6 length long at least!")
   // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,}$",message = "Please enter strong password")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 3, message = "Name have to be 3 character long at least!")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotEmpty(message = "Email must not be empty!")
    @Email(message = "Please enter a valid email!")
    @Column(columnDefinition = "varchar(255) unique not null")
    private String email;

    @NotEmpty(message = "Phone number must not be empty!")
    @Size(min = 10,max = 10,message = "Please enter a valid phone number!")
    @Pattern(regexp = "[0-9]+")
    @Column(columnDefinition = "varchar(10) unique not null")
    private String phoneNumber;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "(admin|customer|store|clinic)",message = "Role must be in admin or customer or store or clinic only")
    @Column(columnDefinition = "varchar(10) not null check (role='admin' or role='customer' or role='store' or role='clinic')")
    private String role;

    @NotEmpty(message = "Address must not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String address;


    // FOR USER
    @Min(value = 18 , message = "Age must be 18 or above!")
    private Integer age;

    //For Store
    @PositiveOrZero(message = "Delivery Fee must be positive number!")
    @Column(columnDefinition = "Double not null")
    private Double deliveryFee=0.0;


    //For both store and clinic
    private Boolean isApproved=false;

    @URL(message = "Url only!")
    private String picture;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

