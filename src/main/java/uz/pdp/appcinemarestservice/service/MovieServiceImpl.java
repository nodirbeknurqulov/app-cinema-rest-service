package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/30/2022  3:43 PM

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final DistributorRepository distributorRepository;
    private final AttachmentService attachmentService;

    /**
     * GET ALL MOVIES
     *
     * @param size   int
     * @param page   int
     * @param search String
     * @return List
     */
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

    /**
     * ADD MOVIE
     *
     * @param file     int
     * @param movieDto MovieDto
     * @return ApiResponse
     */
    @Override
    public ApiResponse addMovie(MultipartFile file, MovieDto movieDto) {
//        private String title;
//        private String description;
//        private int durationInMin;
//        private LocalDate releaseDate;
//        private String trailerVideoUrl;
//        private double minPrice;
//        private double distributorShareInPercentage;

//        private Integer distributorId;
        Optional<Distributor> optionalDistributor = distributorRepository.findById(movieDto.getDistributorId());

//        private List<Integer> genreIds;
//        private List<Integer> actorsId;
        Movie movie = new Movie();
        return null;
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
