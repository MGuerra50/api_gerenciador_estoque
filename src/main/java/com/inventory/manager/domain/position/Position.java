package com.inventory.manager.domain.position;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Position")
@Table(name = "im_positions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isActive;
}
