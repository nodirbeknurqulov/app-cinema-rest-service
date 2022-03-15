package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  8:38 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "waiting_purchase_time")
public class WaitingPurchaseTime extends AbsEntity {
    private int minute;
}
