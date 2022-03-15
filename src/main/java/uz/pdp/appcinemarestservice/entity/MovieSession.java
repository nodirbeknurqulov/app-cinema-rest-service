package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

// Nurkulov Nodirbek 3/15/2022  7:22 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "movie_sessions")
public class MovieSession extends AbsEntity {

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Hall hall;

    private boolean isActive;
}
