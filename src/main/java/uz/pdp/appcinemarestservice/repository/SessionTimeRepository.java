package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.SessionTime;
import uz.pdp.appcinemarestservice.projection.SessionTimeProjection;
import java.util.List;

public interface SessionTimeRepository extends JpaRepository<SessionTime,Integer> {
    @Query(nativeQuery = true, value = "select\n" +
            "       st.id,\n" +
            "       st.time,\n" +
            "       ms.id as sessionId\n" +
            "from session_times st\n" +
            "join movie_sessions ms on st.id = ms.start_time_id\n" +
            "where ms.hall_id = :hallId and " +
            "      ms.movie_announcement_id = :movieAnnouncementId and\n" +
            "      ms.start_date_id = :startDateId")
    List<SessionTimeProjection> getAllTimesByHallIdAndAnnouncementIdAndStartDateId(Integer hallId, Integer movieAnnouncementId, Integer startDateId);
}
