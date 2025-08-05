package com.inventory.manager.domain.position;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Position")
@Table(name = "im_positions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE  im_positions" +
        " SET isactive = false " +
        " WHERE id = ? ")
@SQLRestriction(" isactive = true ")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "isactive")
    private Boolean isActive;
}
