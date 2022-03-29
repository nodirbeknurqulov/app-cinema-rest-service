package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/16/2022  7:44 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.GenreService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    /**
     * GET ALL GENRES BY PAGE
     *
     * @param page int
     * @param size int
     * @return HttpEntity
     */
    @GetMapping
    public HttpEntity<?> getAllGenresByPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Genre> allGenres = genreService.getAllGenres(page, size);
        return ResponseEntity.ok(allGenres);
    }

    /**
     * GET GENRE BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getGenreById(@PathVariable Integer id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.status(genre != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(genre);
    }

    /**
     * ADD GENRE
     *
     * @param genre Genre
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<?> addGenre(@RequestBody Genre genre) {
        ApiResponse apiResponse = genreService.addGenre(genre);
        return ResponseEntity.status(201).body(apiResponse);
    }

    /**
     * UPDATE GENRE
     *
     * @param id    Integer
     * @param genre Genre
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse updateGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    /**
     * DELETE GENRE BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteGenre(@PathVariable Integer id) {
        return genreService.deleteGenre(id);
    }
}
