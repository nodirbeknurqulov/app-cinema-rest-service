package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.MovieDto;
import uz.pdp.appcinemarestservice.repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/26/2022  11:10 AM
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final DistributorRepository distributorRepository;
    private final AttachmentService attachmentService;

    /**
     * GET ALL MOVIES
     *
     * @param page int
     * @param size int
     * @return List
     */

    public List<Movie> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.getContent();
    }

    /**
     * Add movie
     *
     * @param movieDto MovieDto
     * @return Movie
     */
    public ApiResponse addMovie(MovieDto movieDto, MultipartFile request) throws IOException {
        Movie movie = new Movie();

        List<Integer> actorsIds = movieDto.getActorsId();
        List<Actor> actorIdList = new ArrayList<>();
        for (Integer actorsId : actorsIds) {
            Optional<Actor> optionalActor = actorRepository.findById(actorsId);
            if (!optionalActor.isPresent()) {
                return new ApiResponse("Actor not found", false);
            }
            Actor actor = optionalActor.get();
            actorIdList.add(actor);
        }
        movie.setActors(actorIdList);
        ////////////////////////////////////////////////////////////////////////////////////////////

        List<Integer> genreIds = movieDto.getGenreIds();
        List<Genre> genreIdList = new ArrayList<>();
        for (Integer genreId : genreIds) {
            Optional<Genre> optionalGenre = genreRepository.findById(genreId);
            if (!optionalGenre.isPresent()) {
                return new ApiResponse("Genre not found!", false);
            }
            Genre genre = optionalGenre.get();
            genreIdList.add(genre);
        }
        movie.setGenres(genreIdList);
        ////////////////////////////////////////////////////////////////////////////////////////////

        Integer distributorId = movieDto.getDistributorId();
        Optional<Distributor> optionalDistributor = distributorRepository.findById(distributorId);
        if (!optionalDistributor.isPresent()) {
            return new ApiResponse("Distributor not found!", false);
        }
        Distributor distributor = optionalDistributor.get();
        movie.setDistributor(distributor);

        movie.setTitle(movieDto.getTitle());
        movie.setDurationInMinutes(movieDto.getDurationInMin());
        movie.setTrailerVideoUrl(movieDto.getTrailerVideoUrl());
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributorShareInPercentages(movieDto.getDistributorShareInPercentage());

        Attachment upload = attachmentService.uploadFile(request);
        movie.setCoverImage(upload);

        Movie savedMovie = movieRepository.save(movie);
        return new ApiResponse("Movie added!", true, savedMovie);
    }
}
