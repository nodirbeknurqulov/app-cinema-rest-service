package uz.pdp.appcinemarestservice.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class MovieSessionTime extends AbsEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private SessionDate sessionDate;

    private Time time;
}
