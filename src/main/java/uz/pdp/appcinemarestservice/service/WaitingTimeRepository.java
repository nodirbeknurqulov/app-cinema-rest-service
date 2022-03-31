package uz.pdp.appcinemarestservice.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.WaitingPurchaseTime;

import java.util.UUID;

public interface WaitingTimeRepository extends JpaRepository<WaitingPurchaseTime, UUID> {

    @Query(nativeQuery = true,value = "select minute " +
            "from waiting_purchase_times " +
            "order by created_at desc " +
            "limit 1")
    Integer getWaitingMinute();
}
