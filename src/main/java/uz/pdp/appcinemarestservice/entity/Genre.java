package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/16/2022  7:38 AM

import lombok.*;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "genres")
public class Genre extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;
}
