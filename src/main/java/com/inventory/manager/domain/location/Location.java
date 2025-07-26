package com.inventory.manager.domain.location;

import com.inventory.manager.domain.warehouse.Warehouse;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Locations")
@Table(name = "im_locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouses_id")
    private Warehouse warehouse;
    private String locationCode;
    private Boolean isActive;
}
