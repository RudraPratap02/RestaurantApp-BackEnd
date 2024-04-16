package com.RapidGrill.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable // used to embed the RestaurantDto class in the User class
public class RestaurantDto {

    private String title;

    @Column(length = 1000) // length of the description is set to 1000
    private List<String> images;

    private String description;
    private Long id;
}
