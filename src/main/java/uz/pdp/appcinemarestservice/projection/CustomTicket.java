package uz.pdp.appcinemarestservice.projection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface CustomTicket {

    Integer getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    String getHallName();

    Double getPrice();

    Integer getMovieId();

    String getMovieName();

    LocalDate getStartDate();

    LocalTime getStartTime();
}
