package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType,Integer> {
}
