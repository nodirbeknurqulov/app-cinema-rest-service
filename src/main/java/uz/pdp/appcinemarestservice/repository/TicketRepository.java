package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.projection.CustomTicketForCart;
import uz.pdp.appcinemarestservice.projection.TicketProjection;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(nativeQuery = true, value = "" +
            "select " +
            "id," +
            "status " +
            "from tickets" +
            "where id = :id")
    CustomTicketForCart getTicketByIdForCart(Integer id);

    @Query(nativeQuery = true, value = "" +
            "select " +
            "distinct " +
            "* " +
            "from tickets" +
            " where movie_session_id = :movieSessionId" +
            " and seat_id = :seatId" +
            " and status <> 'REFUNDED'")
    CustomTicketForCart checkSeatIfAvailable(Integer movieSessionId, Integer seatId);

    @Query(nativeQuery = true, value = "" +
            "select m.title,\n" +
            "       t.price\n" +
            "from tickets t\n" +
            "         join movie_sessions ms on ms.id = t.movie_session_id\n" +
            "         join movie_announcement ma on ma.id = ms.movie_announcement_id\n" +
            "         join movies m on ma.movie_id = m.id\n" +
            "            where t.user_id= :userId\n")
    List<TicketProjection> getTicketsByUserId(Integer userId);



    @Query(nativeQuery = true, value = "select t.id as id,\n" +
            "       t.price as price,\n" +
            "       m.title as movieTitle\n" +
            "from tickets t\n" +
            "         join movie_sessions ms on t.movie_session_id = ms.id\n" +
            "join movie_announcement ma on ms.movie_announcement_id = ma.id\n" +
            "join movies m on ma.movie_id = m.id\n" +
            "where t.user_id = :userId and t.status = 'NEW'")
    List<TicketProjection>  getTicketByUserId(Integer userId);


    List<Ticket> findByUserIdAndStatus(Integer user_id, TicketStatus status);
}
