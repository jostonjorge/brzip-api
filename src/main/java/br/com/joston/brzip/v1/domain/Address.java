package br.com.joston.brzip.v1.domain;

import lombok.Data;

@Data
public class Address {
    private String street = "";
    private String complement = "";
    private String district = "";
    private String city = "";
    private String state = "";
    private String zipCode = "";
}