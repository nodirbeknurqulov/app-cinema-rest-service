package uz.pdp.appcinemarestservice.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "movie_sessions")
public class MovieSession extends AbsEntity {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne()
    private MovieAnnouncement movieAnnouncement;

    @ManyToOne( )
    private Hall hall;

    @ManyToOne()
    private SessionDate startDate;

    @ManyToOne()
    private SessionTime startTime;

    @ManyToOne()
    private SessionTime endTime;
}
