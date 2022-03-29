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
    public HttpEntity<?> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        List<Movie> allMovies = movieService.getAllMovies(page, size);
        return ResponseEntity.ok(allMovies);
    }

    /**
     * Add movie
     * @param movieDto MovieDto
     * @return HttpEntity
     */
    @PostMapping
    public HttpEntity<?> addMovie(@RequestPart(name = "movie") MovieDto movieDto,
                                  @RequestPart(name = "file") MultipartFile request) throws IOException {
        ApiResponse apiResponse = movieService.addMovie(movieDto, request);
        return ResponseEntity.status(apiResponse.isSuccess()? 200: 404).body(apiResponse);
    }
}
