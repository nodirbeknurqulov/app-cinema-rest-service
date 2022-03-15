package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

// Nurkulov Nodirbek 3/15/2022  8:41 AM
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "refund_charge_fees")
public class RefundChargeFee extends AbsEntity {
    @Column(nullable = false)
    private int intervalInMinutes;

    @Column(nullable = false)
    private double percentage;
}
