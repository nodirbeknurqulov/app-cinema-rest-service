package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.MovieAnnouncement;

public interface MovieAnnouncementRepository extends JpaRepository<MovieAnnouncement,Integer> {
}
