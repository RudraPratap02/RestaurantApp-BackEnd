package com.RapidGrill.model;

import java.util.ArrayList; // Add missing import statement

import java.util.List;

import com.RapidGrill.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // This is used to hide the password when fetching the user
    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore // when fetching all the deatils of user, it will not fetch the orders
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // when fetching all the deatils of user, it will fetch
                                                                 // the orders
    private List<Order> orders = new ArrayList<>();

    @ElementCollection // This is used to store the list of strings in the database
    private List<RestaurantDto> favourites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // CascadeType.ALL is used to delete the address of the
                                                                // user if the user is deleted
    private List<Address> addresses = new ArrayList<>();
}
