package uz.pdp.appcinemarestservice.service.interfaces;

import org.springframework.http.ResponseEntity;
import uz.pdp.appcinemarestservice.payload.MovieDto;

public interface MovieService {
    ResponseEntity getAllMovies(int pageNumber,int size, String search, String sort, boolean direction);

    ResponseEntity getMovieById(Integer id);

    ResponseEntity addMovie(MovieDto movieDto);

    ResponseEntity updateMovie(Integer id);

    ResponseEntity deleteMovieById(Integer id);
}
