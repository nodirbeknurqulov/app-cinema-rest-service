package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Hall;
import uz.pdp.appcinemarestservice.entity.Row;

public interface HallRepository extends JpaRepository<Hall,Integer> {
}
