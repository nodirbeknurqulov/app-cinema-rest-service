package uz.pdp.appcinemarestservice.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovieSessionProjection {

    UUID getMovieAnnouncementId(); // afisha id

    UUID getMovieId();

    String getMovieTitle();

    UUID getMovieCoverImgId();

    UUID getStartDateId();

    LocalDate getStartDate();

    @Value("#{@hallRepository.getHallsAndTimesByMovieAnnouncementIdAndStartDateId(target.movieAnnouncementId, target.startDateId)}")
    List<HallAndTimesProjectionForSession> getHalls();


}
