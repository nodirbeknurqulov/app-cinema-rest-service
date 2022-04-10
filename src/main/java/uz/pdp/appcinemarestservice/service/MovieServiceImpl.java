package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/30/2022  3:43 PM

import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.interfaces.MovieService;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.projection.CustomMovie;
import uz.pdp.appcinemarestservice.repository.ActorRepository;
import uz.pdp.appcinemarestservice.repository.DistributorRepository;
import uz.pdp.appcinemarestservice.repository.GenreRepository;
import uz.pdp.appcinemarestservice.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<CustomMovie> getAllMovies(int size, int page, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomMovie> moviePage = movieRepository.findAllByPage(pageable, search);
        return moviePage.getContent();
    }

    @Override
    public HttpEntity<?> getMovieById(Integer id) {
        return null;
    }

    @Override
    public HttpEntity<?> addMovie(MultipartFile file, MovieDto movieDto) {
        return ResponseEntity.ok("Movie saved!");
    }

    @Override
    public ApiResponse updateMovie(Integer id, MultipartFile file, MovieDto movieDto) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteMovie(Integer id) {
        return null;
    }
}
