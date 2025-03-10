package dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order;

import dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.DishEntity;
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
public class DishOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private DishEntity dish;
}
