package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.projection.CustomTicketForCart;
import uz.pdp.appcinemarestservice.projection.TicketProjection;

import java.util.List;

public interface HistoryRepository extends JpaRepository<TransactionHistory, Integer> {

}
