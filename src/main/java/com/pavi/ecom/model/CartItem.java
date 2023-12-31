package com.pavi.ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	private String size;
	private int quantity;
	private Integer price;
	private Integer discountedPrice;
	private Long userId;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cart=" + cart + ", size=" + size + ", quantity=" + quantity + ", price="
				+ price + ", discountedPrice=" + discountedPrice + ", userId=" + userId + ", product=" + product + "]";
	}
	
	
}
