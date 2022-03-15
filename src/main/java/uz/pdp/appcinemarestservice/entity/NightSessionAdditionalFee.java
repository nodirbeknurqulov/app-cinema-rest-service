package uz.pdp.appcinemarestservice.entity;

// Nurkulov Nodirbek 3/15/2022  7:33 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "night_session_add_fee")
public class NightSessionAdditionalFee extends AbsEntity {
    private double percentage;
    private Date date;
}
