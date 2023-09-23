package com.pavi.ecom.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentInformationDTO {

    private String cardholderName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
}