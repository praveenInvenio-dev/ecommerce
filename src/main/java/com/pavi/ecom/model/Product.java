package com.pavi.ecom.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private Double price;
	private int discountPrice;
	private int discountPresent;
	private int quantity;
	private String brand;
	private String color;
	@Embedded
	@ElementCollection
	@Column(name="sizes")
	private Set<Size> sizes = new HashSet<>();
	private String imgurl;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Rating> ratings;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> review;
	
	private int numRatings;
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;

}
