package com.RapidGrill.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContactInformation {

    private String email;
    private String phoneNumber;
    private String twitter;
    private String facebook;
}
