package uz.pdp.appcinemarestservice.controller;

// Nurkulov Nodirbek 3/16/2022  7:41 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Director;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Genre;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.DirectorService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;


    /**
     * GET ALL DIRECTORS BY PAGEABLE
     * @param page int
     * @param size int
     * @return HttpEntity
     */
    @GetMapping
    public HttpEntity<?> getAllDirectorsByPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Director> allDirectors = directorService.getAllDirectors(page, size);
        return ResponseEntity.ok(allDirectors);
    }

    /**
     * GET DIRECTOR BY ID
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ApiResponse getDirectorById(@PathVariable Integer id) {
        return directorService.getDirectorById(id);
    }

    /**
     * ADD DIRECTOR
     * @param director Director
     * @return HttpEntity
     */
    @PostMapping
    public HttpEntity<?> addDirector(@RequestBody Director director) {
        Director newDirector = directorService.addDirector(director);
        return ResponseEntity.status(201).body(newDirector);
    }

    /**
     * UPDATE DIRECTOR BY ID
     * @param id Integer
     * @param director Director
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse updateDirector(@PathVariable Integer id, @RequestBody Director director) {
        return directorService.updateDirector(id, director);
    }

    /**
     * DELETE DIRECTOR BY ID
     * @param id Integer
     * @return HttpEntity
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDirector(@PathVariable Integer id) {
        boolean deleteDirector = directorService.deleteDirector(id);
        if (deleteDirector) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
