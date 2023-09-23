package com.pavi.ecom.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsersDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private String mobile;
	private List<AddressDTO> address;
	private List<PaymentInformationDTO> paymentInformation;
	private List<RatingDTO> rating;
	private List<ReviewDTO> review;
	private CartDTO cart;
}