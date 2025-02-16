package dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String urlImage;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
