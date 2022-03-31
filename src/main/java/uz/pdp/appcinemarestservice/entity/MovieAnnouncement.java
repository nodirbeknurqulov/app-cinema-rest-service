package uz.pdp.appcinemarestservice.entity;

import lombok.*;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieAnnouncement extends AbsEntity {

    @OneToOne
    private Movie movie;

    @Column(nullable = false)
    public boolean isActive;
}
