package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/16/2022  7:44 AM

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final GenreRepository genreRepository;

    public ApiResponse getAllGenres() {
        List<Genre> genreList = genreRepository.findAll();
        if (genreList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, genreList);
    }

    public ApiResponse getGenre(Integer id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!", false);
        }
        return new ApiResponse("Success!", true, optionalGenre.get());
    }

    public ApiResponse addGenre(Genre genre) {
        try {
            Genre newGenre = new Genre();
            newGenre.setName(genre.getName());
            Genre save = genreRepository.save(newGenre);
            return new ApiResponse("Successfully added!!", true, save);
        } catch (Exception e) {
            return new ApiResponse("Successfully added!!", false);
        }

    }

    public ApiResponse editGenre(Integer id, Genre genre) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!", false);
        }
        try {
            Genre editingGenre = optionalGenre.get();
            editingGenre.setName(genre.getName());
            Genre save = genreRepository.save(editingGenre);
            return new ApiResponse("Successfully edited!!", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe genre already exists!", false);
        }
    }

    public ApiResponse deleteGenre(Integer id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!", false);
        }
        genreRepository.deleteById(id);
        return new ApiResponse("Successfully deleted!!", true);
    }
}
