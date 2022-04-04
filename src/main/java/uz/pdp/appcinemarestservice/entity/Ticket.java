package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;

// Nurkulov Nodirbek 3/15/2022  8:26 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tickets")
public class Ticket extends AbsEntity {
    @OneToOne
    MovieSession movieSession;

    @OneToOne
    Seat seat;

    @OneToOne
    Attachment qrCode;

    @Column(nullable = false)
    Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TicketStatus status = TicketStatus.NEW;

    @ManyToOne
    User user;
}
