package uz.pdp.appcinemarestservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface MovieService {

    HttpEntity getAllMovies(int page, int size, String search, String sort, boolean direction);

    HttpEntity getMovieById(UUID id);

    HttpEntity saveMovie(MovieDto movieDto);

    HttpEntity deleteMovie(UUID id);




}
