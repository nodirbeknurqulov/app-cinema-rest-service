package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.MovieSession;

public interface SessionRepository extends JpaRepository<MovieSession, Integer> {
}
