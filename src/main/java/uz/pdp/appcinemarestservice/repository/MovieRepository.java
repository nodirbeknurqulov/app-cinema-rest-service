package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 3/16/2022  7:52 AM

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.projection.CustomMovie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Page<CustomMovie> findAllByPage(Pageable pageable, String search);
}
