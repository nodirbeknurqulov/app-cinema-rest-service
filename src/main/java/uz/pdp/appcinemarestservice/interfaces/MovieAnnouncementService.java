package uz.pdp.appcinemarestservice.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieAnnouncementDto;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.projection.CustomMovie;
import java.util.List;

public interface MovieAnnouncementService {

    ApiResponse getAllMovieAnnouncement(int page, int size);
    ApiResponse getMovieAnnouncementById(Integer movieAnnouncement_id);
    ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse editMovieAnnouncement(Integer movieAnnouncement_id, MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse deleteMovieAnnouncement(Integer movieAnnouncement_id);
}
