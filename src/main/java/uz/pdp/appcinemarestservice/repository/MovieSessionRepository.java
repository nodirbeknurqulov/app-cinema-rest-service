package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.MovieSession;
import uz.pdp.appcinemarestservice.projection.MovieSessionProjection;

public interface MovieSessionRepository extends JpaRepository<MovieSession,Integer> {
    @Query(nativeQuery = true, value = "select distinct ma.id            as movieAnnouncementId,\n" +
            "                ma.movie_id      as movieId,\n" +
            "                m.title          as movieTitle,\n" +
            "                m.cover_image_id as movieCoverImgId,\n" +
            "                sd.id            as startDateId,\n" +
            "                sd.date          as startDate\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcement ma on ms.movie_announcement_id = ma.id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join session_dates sd on ms.start_date_id = sd.id\n" +
            "where ma.is_active\n" +
            "  and lower(m.title) like lower(concat('%', :search, '%'))\n" +
            "  and sd.date >= cast(now() as date)\n" +
            "order by sd.date")
    Page<MovieSessionProjection> findAllSessionsByPage(Pageable pageable, String search);

}
