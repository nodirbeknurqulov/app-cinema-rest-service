package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/31/2022  1:37 PM

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appcinemarestservice.entity.MovieAnnouncement;
import uz.pdp.appcinemarestservice.interfaces.MovieAnnouncementService;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieAnnouncementDto;
import uz.pdp.appcinemarestservice.repository.MovieAnnouncementRepository;
import uz.pdp.appcinemarestservice.repository.MovieRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieAnnouncementServiceImpl implements MovieAnnouncementService {
    private final MovieAnnouncementRepository movieAnnouncementRepository;
    private final MovieRepository movieRepository;

    @Override
    public ApiResponse getAllMovieAnnouncement(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieAnnouncement> movieAnnouncements = movieAnnouncementRepository.findAll(pageable);
        if (movieAnnouncements.getSize() == 0){
            return new ApiResponse("Not found!!!",false);
        }
        return new ApiResponse("Success!", false);
    }

    @Override
    public ApiResponse getMovieAnnouncementById(Integer movieAnnouncement_id) {
        return null;
    }

    @Override
    public ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto) {
        return null;
    }

    @Override
    public ApiResponse editMovieAnnouncement(Integer movieAnnouncement_id, MovieAnnouncementDto movieAnnouncementDto) {
        return null;
    }

    @Override
    public ApiResponse deleteMovieAnnouncement(Integer movieAnnouncement_id) {
        return null;
    }
}
