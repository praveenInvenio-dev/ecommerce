package com.pavi.ecom.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation extends BaseEntity {

	private String cardholderName;
	private String cardNumber;
	private LocalDate expirationDate;
	private String cvv;
	
}
