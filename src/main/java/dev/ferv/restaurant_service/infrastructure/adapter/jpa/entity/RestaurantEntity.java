package dev.ferv.restaurant_service.infrastructure.adapter.jpa.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String nit;
    private String phoneNumber;
    private String urlLogo;

    @OneToMany(mappedBy = "restaurant")
    private List<DishEntity> dishes;

    @ElementCollection
    @CollectionTable(name = "employeesIdList", joinColumns = @JoinColumn(name = "employeeId"))
    @Column(name = "employeesId")
    private List<Long> employeesId;

    private Long ownerId;

}
