package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.PriceCategory;

import java.util.UUID;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Integer> {
}
