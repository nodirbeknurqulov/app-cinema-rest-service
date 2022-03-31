package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.Hall;
import uz.pdp.appcinemarestservice.entity.Row;
import uz.pdp.appcinemarestservice.projection.HallAndTimesProjectionForSession;

import java.util.List;
import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall,Integer> {
    @Query(nativeQuery = true, value = "select\n" +
            "       h.id,\n" +
            "       h.name,\n" +
            "       ms.movie_announcement_id as movieAnnouncementId,\n" +
            "       ms.start_date_id as startDateId\n" +
            "from movie_sessions ms\n" +
            "join halls h on h.id = ms.hall_id\n" +
            "where ms.movie_announcement_id =:movieAnnouncementId\n" +
            "and ms.start_date_id = :startDateId\n" +
            "\n")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(Integer movieAnnouncementId, Integer startDateId);
}
