package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Director;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
}
