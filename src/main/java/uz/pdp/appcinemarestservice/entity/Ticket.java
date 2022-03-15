package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

// Nurkulov Nodirbek 3/15/2022  8:26 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tickets")
public class Ticket extends AbsEntity {
    @ManyToOne
    private MovieSession movieSession;

    @OneToOne
    private Seat seat;

    @OneToOne
    private Attachment qr_code;

    private double price;

    @ManyToOne
    private Cart cart;
}
