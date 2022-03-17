package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  11:20 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "session_halls")
public class SessionHall extends AbsEntity {

    @ManyToOne
    private MovieSchedule movieSession;

    @ManyToOne
    private Hall hall;
}
