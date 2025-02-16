package dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity.order;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id") //only the foreign key not the WHOLE object
    private Long restaurantId; 

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //bidirectional
    @JoinColumn(name = "dishes_order_id")
    private List<DishOrderEntity> dishes;

}
