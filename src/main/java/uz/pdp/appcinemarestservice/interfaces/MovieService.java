package uz.pdp.appcinemarestservice.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.projection.CustomMovie;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface MovieService {
    List<CustomMovie> getAllMovies(int size, int page, String search);

    HttpEntity<?> getMovieById(Integer id);

    HttpEntity addMovie(MultipartFile file, MovieDto movieDto);

    ApiResponse updateMovie(Integer id, MultipartFile file, MovieDto movieDto);

    HttpEntity<?> deleteMovie(Integer id);
}
