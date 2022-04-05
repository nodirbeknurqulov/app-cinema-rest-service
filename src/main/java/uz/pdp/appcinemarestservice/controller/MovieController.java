package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.projection.CustomMovie;
import uz.pdp.appcinemarestservice.service.MovieServiceImpl;
import uz.pdp.appcinemarestservice.utill.Constant;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

// Nurkulov Nodirbek 3/16/2022  7:52 AM

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;

    /**
     * GET ALL MOVIES
     * @param page int
     * @param size int
     * @return List
     */
    @GetMapping
    public HttpEntity<?> getAllMovies(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size,
                                      @RequestParam(name = "search", defaultValue = "") String search) {
        List<CustomMovie> allMovies = movieService.getAllMovies(page, size, search);
        return ResponseEntity.ok(allMovies);
    }

    /**
     * ADD MOVIE
     * @param movieDto MovieDto
     * @return HttpEntity
     */
    @PostMapping
    public HttpEntity<?> addMovie(@RequestPart(name = "add-movie-json") @Valid @RequestBody MovieDto movieDto,
                                  @RequestPart(name = "file") MultipartFile file) {
        return movieService.addMovie(file, movieDto);
    }
}
