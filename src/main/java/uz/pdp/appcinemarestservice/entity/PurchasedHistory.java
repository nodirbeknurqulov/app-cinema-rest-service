package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  8:45 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "purchased_histories")
public class PurchasedHistory extends AbsEntity {

    @ManyToOne
    private PaymentType paymentType;

    private Date date;
}
