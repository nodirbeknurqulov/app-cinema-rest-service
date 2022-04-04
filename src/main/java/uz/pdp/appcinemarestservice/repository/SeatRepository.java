package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.Seat;
import uz.pdp.appcinemarestservice.projection.AvailableProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByRowId(Integer row_id);

//    @Query(value = "select * from seats s join hall_rows hr on hr.id = s.row_id " +
//            "join halls h on h.id = hr.hall_id " +
//            "where h.id = :hallId", nativeQuery = true)
//    Optional<Seat> findByHallId(Integer hallId);

    @Query(nativeQuery = true,
            value = "select s.id,\n" +
                    "       s.number              as seatNumber,\n" +
                    "       hr.number             as rowNumber,\n" +
                    "       true                  as available\n" +
                    "from seats s\n" +
                    "         join h_rows hr on s.row_id = hr.id\n" +
                    "         join halls h on hr.hall_id = h.id\n" +
                    "         join movie_sessions ms on h.id = ms.hall_id\n" +
                    "where s.id not in (\n" +
                    "    select t.seat_id\n" +
                    "    from tickets t\n" +
                    "             join movie_sessions ms on ms.id = t.movie_session_id\n" +
                    "    where t.status <> 'REFUNDED'\n" +
                    "      and ms.id = :movieSessionId\n" +
                    ")\n" +
                    "  and ms.id = :movieSessionId\n" +
                    "union\n" +
                    "select s.id,\n" +
                    "       s.number              as seatNumber,\n" +
                    "       hr.number             as rowNumber,\n" +
                    "       false                 as available\n" +
                    "from tickets t\n" +
                    "         join seats s on t.seat_id = s.id\n" +
                    "         join h_rows hr on s.row_id = hr.id\n" +
                    "         join movie_sessions ms on ms.id = t.movie_session_id\n" +
                    "where t.status <> 'REFUNDED'\n" +
                    "  and ms.id = :movieSessionId\n" +
                    "order by rowNumber, seatNumber")
    List<AvailableProjection> findAvailableSeatsBySessionId(Integer movieSessionId);

//    @Query(nativeQuery = true, value = "select distinct (coalesce(pc.additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
//            "                           (coalesce(h.vip_additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
//            "                           m.min_price as price\n" +
//            "                    from seats s\n" +
//            "                             join hall_rows r on r.id = s.row_id\n" +
//            "                             join halls h on r.hall_id = h.id\n" +
//            "                             join movie_sessions ms on h.id = ms.hall_id\n" +
//            "                             join movie_sessions ms1 on h.id = ms1.hall_id\n" +
//            "                             join movie_announcement ma on ma.id = ms1.movie_announcement_id\n" +
//            "                             join movies m on m.id = ma.movie_id\n" +
//            "                             join price_categories pc on s.price_categories_id = pc.id\n" +
//            "                    where ms.id = :movieSessionId\n" +
//            "                      and s.id = :seatId")
//    Double getPriceOfSeatBySeatIdAndMovieSessionId(Integer seatId, Integer movieSessionId);
}
