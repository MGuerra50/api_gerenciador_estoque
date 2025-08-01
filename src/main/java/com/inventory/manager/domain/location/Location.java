package com.inventory.manager.domain.location;

import com.inventory.manager.domain.warehouse.Warehouse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Locations")
@Table(name = "im_locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE im_locations " +
        " SET isactive = false" +
        " WHERE id = ? ")
@SQLRestriction(" isactive = true ")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouses_id")
    private Warehouse warehouse;
    private String locationCode;
    @Column(name = " isactive ")
    private Boolean isActive;
}
