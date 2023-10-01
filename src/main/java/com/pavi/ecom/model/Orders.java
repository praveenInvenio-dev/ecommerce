package com.pavi.ecom.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavi.ecom.custom.CustomIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends BaseEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String orderId;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	@JsonIgnore
	private Users user;
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	private List<Orderitems> orderItems = new ArrayList<>();
	
	private LocalDateTime orderDate;
	private LocalDateTime deliveryDate;
	
//	@OneToOne
//	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails;
	
	private double totalPrice;
	private double totalDiscountedPrice;
	private Integer discount;
	private String orderStatus;
	private int totalItem;
	
	
}
