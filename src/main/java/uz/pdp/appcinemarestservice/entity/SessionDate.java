package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

// Nurkulov Nodirbek 3/15/2022  7:22 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "session_dates")
public class SessionDate extends AbsEntity {

    private Date date;

//    @OneToMany
//    private List<MovieSession> movieSession;

//    @OneToMany
//    private List<SessionTime> sessionTime;

    @ManyToOne
    private SessionHall sessionHall;
}
