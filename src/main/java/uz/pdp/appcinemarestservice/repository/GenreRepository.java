package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 3/16/2022  7:46 AM

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
