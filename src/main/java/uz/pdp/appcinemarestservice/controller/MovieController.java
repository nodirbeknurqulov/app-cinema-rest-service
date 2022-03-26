package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.service.MovieService;
import uz.pdp.appcinemarestservice.utill.Constant;

import java.io.IOException;
import java.util.List;

// Nurkulov Nodirbek 3/16/2022  7:52 AM

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * GET ALL MOVIES
     *
     * @param page int
     * @param size int
     * @return List
     */
    @GetMapping
    public HttpEntity<?> getAllMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Movie> allMovies = movieService.getAllMovies(page, size);
        return ResponseEntity.ok(allMovies);
    }

    /**
     * Add movie
     * @param movieDto MovieDto
     * @return HttpEntity
     */
    @PostMapping
    public HttpEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        ApiResponse apiResponse = movieService.addMovie(movieDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

}
