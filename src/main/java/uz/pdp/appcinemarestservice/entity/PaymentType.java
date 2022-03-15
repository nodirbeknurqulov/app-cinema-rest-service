package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  8:51 AM

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
@Entity(name = "paymentTypes")
public class PaymentType extends AbsEntity {
    private String name;

    private double commissionFeeInPercentage;
}
