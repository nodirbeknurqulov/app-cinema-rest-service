package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 3/16/2022  7:52 AM

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.projection.CustomMovie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "select cast(m.id as varchar)      as id,\n" +
            "       title,\n" +
            "       cast(release_date as date) as releaseDate,\n" +
            "       cast(a.id as varchar)      as coverImgId\n" +
            "from movies m\n" +
            "         join attachments a on m.cover_image_id = a.id\n" +
            "where lower(title) like lower(concat('%', :search, '%'))", nativeQuery = true)
    Page<CustomMovie> findAllByPage(Pageable pageable, @Param("search") String search);

}
