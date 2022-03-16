package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/16/2022  7:41 AM

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Director;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.DirectorService;

import java.util.UUID;


@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping
    public HttpEntity<?> getAllDirectors(){
        ApiResponse allDirectors = directorService.getAllDirectors();
        return ResponseEntity.status(allDirectors.isSuccess()?200:204).body(allDirectors);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDirector(@PathVariable Integer id){
        ApiResponse apiResponse = directorService.getDirector(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PostMapping()
    public HttpEntity<?> addDirector(@RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director){
        ApiResponse apiResponse = directorService.addDirector(file, director);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDirector(@PathVariable Integer id,@RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director){
        ApiResponse apiResponse = directorService.editDirector(id, file, director);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> addDirector(@PathVariable Integer id){
        ApiResponse apiResponse = directorService.deleteDirector(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
