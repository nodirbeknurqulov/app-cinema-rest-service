package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
    @Query(nativeQuery = true, value = "select * from transaction_histories th\n" +
            "join transaction_histories_tickets tht on th.id = tht.transaction_id\n" +
            "where tht.ticket_id = :ticketId order by th.created_at limit 1")
    TransactionHistory getByTicketId(Integer ticketId);
}
