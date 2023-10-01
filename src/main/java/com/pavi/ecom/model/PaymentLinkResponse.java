package com.pavi.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkResponse {
	
	private String paymentLinkId;
	private String paymentLinkResponse;

}
