package uz.pdp.appcinemarestservice.projection;

import uz.pdp.appcinemarestservice.entity.Ticket;

import java.util.UUID;

public interface CustomTicketForCart {
    Integer getId();

    Ticket getStatus();
}
