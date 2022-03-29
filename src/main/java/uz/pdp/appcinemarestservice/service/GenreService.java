package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/16/2022  7:44 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class GenreService {

    private final GenreRepository genreRepo;

    /**
     * GET ALL GENRES BY PAGEABLE
     * @param page int
     * @param size int
     * @return List
     */
    public List<Genre> getAllGenres(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Genre> genrePage = genreRepo.findAll(pageRequest);
        return genrePage.getContent();
    }

    /**
     * GET GENRE BY ID
     * @param id Integer
     * @return ApiResponse
     */
    public Genre getGenreById(Integer id) {
        Optional<Genre> optionalGenre = genreRepo.findById(id);
       return optionalGenre.orElse(null);
    }

    /**
     * ADD GENRE
     * @param genre Genre
     * @return ApiResponse
     */
    public ApiResponse addGenre(Genre genre) {
        Genre genre1 = new Genre();
        genre1.setName(genre.getName());
        Genre savedGenre = genreRepo.save(genre1);
        return new ApiResponse("Genre added!", true, savedGenre);
    }

    /**
     * UPDATE GENRE
     * @param id Integer
     * @param genre Genre
     * @return ApiResponse
     */
    public ApiResponse updateGenre(Integer id, Genre genre){
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre1 = optionalGenre.get();
            genre1.setName(genre.getName());
            Genre save = genreRepo.save(genre1);
            return new ApiResponse("Genre updated!", true, save);
        }
        return new ApiResponse("Genre not found!", false, null);
    }

    /**
     * DELETE GENRE BY ID
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteGenre(Integer id){
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            genreRepo.delete(genre);
            return new ApiResponse("Genre deleted!", true);
        }
        return new ApiResponse("Genre not found!", false);
    }
}