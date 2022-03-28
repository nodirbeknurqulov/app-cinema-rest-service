package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
