package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.SessionDate;

public interface SessionDateRepository extends JpaRepository<SessionDate,Integer> {
}
