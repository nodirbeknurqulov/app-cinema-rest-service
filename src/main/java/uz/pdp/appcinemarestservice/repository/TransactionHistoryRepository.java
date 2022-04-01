package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
}
