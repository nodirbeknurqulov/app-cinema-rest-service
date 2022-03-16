package uz.pdp.appcinemarestservice.service;

// Nurkulov Nodirbek 3/16/2022  12:02 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.projection.CustomMovie;
import uz.pdp.appcinemarestservice.repository.MovieRepository;
import uz.pdp.appcinemarestservice.service.interfaces.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public ResponseEntity<ApiResponse> getAllMovies(int pageNumber, int size, String search, String sort, boolean direction) {
        Pageable pageable = PageRequest.of(
                pageNumber - 1,
                size,
                direction ? Sort.Direction.ASC : Sort.Direction.DESC,
                sort);

        try {
            Page<CustomMovie> all = movieRepository.findAllByPage(
                    pageable, search
            );
            return ResponseEntity.ok(new ApiResponse("success", true, all));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("error!!!", false, null));
        }

    }


    @Override
    public ResponseEntity getMovieById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity addMovie(MovieDto movieDto) {
        return null;
    }

    @Override
    public ResponseEntity updateMovie(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity deleteMovieById(Integer id) {
        return null;
    }
}
