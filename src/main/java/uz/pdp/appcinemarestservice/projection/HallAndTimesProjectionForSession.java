package uz.pdp.appcinemarestservice.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface HallAndTimesProjectionForSession {

    Integer getId();

    String getName();


//    UUID getMovieAnnouncementId();

//    UUID getStartDateId();

    @Value("#{@sessionTimeRepository.getAllTimesByHallIdAndAnnouncementIdAndStartDateId(target.id, target.movieAnnouncementId, target.startDateId)}")
    List<SessionTimeProjection> getTimes();


}
