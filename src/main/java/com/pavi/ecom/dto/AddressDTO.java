package com.pavi.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String firstName;
    private String city;
    private String state;
    private String zipCode;
    private String mobile;
    private Long userid;
}