package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.entity.MovieSession;
import uz.pdp.appcinemarestservice.service.MovieSessionService;
import uz.pdp.appcinemarestservice.utill.Constant;

import java.util.List;

// Nurkulov Nodirbek 3/31/2022  4:49 PM
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-session")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;

    /**
     * GET ALL MOVIE SESSIONS
     * @param page int
     * @param size int
     * @param search String
     * @return HttpEntity
     */
    @GetMapping
    public HttpEntity<?> getAllMovieSessions(
            @RequestParam(name = "size",defaultValue = "0") int page,
            @RequestParam(name = "page",defaultValue = "10") int size,
            @RequestParam(name = "search", defaultValue = "") String search) {
        HttpEntity<?> allMovieSessions = movieSessionService.getAllMovieSessions(page, size, search);
        return ResponseEntity.ok(allMovieSessions);
    }
}
