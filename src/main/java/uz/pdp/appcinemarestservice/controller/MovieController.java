package uz.pdp.appcinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.service.interfaces.MovieService;
import uz.pdp.appcinemarestservice.utill.Constant;

import java.util.List;

// Nurkulov Nodirbek 3/16/2022  7:52 AM

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public HttpEntity getAllMovies(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ) {
        return movieService.getAllMovies(page, size, search, sort, true);
    }

//    @PostMapping
//    public HttpEntity addMovie(){
//
//    }

}
