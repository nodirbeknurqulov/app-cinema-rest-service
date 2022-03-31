package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.MovieSession;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.projection.MovieSessionProjection;
import uz.pdp.appcinemarestservice.repository.MovieSessionRepository;

import java.util.List;

// Nurkulov Nodirbek 3/31/2022  4:58 PM
@Service
@RequiredArgsConstructor
public class MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;

    /**
     * GET ALL MOVIE SESSIONS
     *
     * @param page   int
     * @param size   int
     * @param search String
     * @return HttpEntity
     */
    public HttpEntity<?> getAllMovieSessions(int page, int size, String search) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<MovieSessionProjection> allSessionsByPage = movieSessionRepository.findAllSessionsByPage(pageable, search);
        return ResponseEntity.ok(new ApiResponse("Success", true, allSessionsByPage));
    }


}
