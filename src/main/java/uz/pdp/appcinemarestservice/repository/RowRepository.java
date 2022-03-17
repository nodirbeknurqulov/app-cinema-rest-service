package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.Row;

public interface RowRepository extends JpaRepository<Row,Integer> {
}
