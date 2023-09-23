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
public class PaymentDetails {
	
	private String paymentMethod;
	private String paymentStatus;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorPaymentLinkReferenceId;
	private String razorPaymentLinkStatus;
	private String razorPaymentPaymentId;
	

}
