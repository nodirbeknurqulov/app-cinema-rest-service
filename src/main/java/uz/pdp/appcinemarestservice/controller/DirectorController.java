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
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.DirectorService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ApiResponse getAllDirector() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ApiResponse getDirectorById(@PathVariable Integer id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public ApiResponse addDirector(@RequestPart(name = "director") Director director,
                                   MultipartHttpServletRequest request) throws IOException {
        return directorService.addDirector(director, request);
    }

    @PutMapping("/{id}")
    public ApiResponse updateDirector(@PathVariable Integer id,
                                      @RequestPart(name = "director") Director director,
                                      MultipartFile file) throws IOException {
        return directorService.updateDirector(id, director, file);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteDirector(@PathVariable Integer id) {
        return directorService.deleteDirector(id);
    }

}
