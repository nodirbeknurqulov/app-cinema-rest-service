package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/16/2022  7:44 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.GenreService;

import java.util.UUID;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ApiResponse getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ApiResponse getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public ApiResponse addGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @PutMapping("/{id}")
    public ApiResponse updateGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteGenre(@PathVariable Integer id) {
        return genreService.deleteGenre(id);
    }
}
