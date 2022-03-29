package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/16/2022  7:21 AM

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.ActorService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    /**
     * GET ALL Actors BY PAGE
     *
     * @param page int
     * @param size int
     * @return HttpEntity
     */
    @GetMapping
    public HttpEntity<?> getAllActorsByPageable(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        List<Actor> allActors = actorService.getAllActors(page, size);
        return ResponseEntity.ok(allActors);
    }

    /**
     * GET ACTOR BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getActor(@PathVariable Integer id){
        ApiResponse apiResponse = actorService.getActorById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    /**
     * ADD ACTOR
     *
     * @param actor   Actor
     * @param request MultipartFile
     * @return Httpentity
     * @throws IOException throw
     */
    @PostMapping
    public HttpEntity<?> addActor(@RequestPart(name = "json") Actor actor,
                                  @RequestPart(name = "file") MultipartFile request) throws IOException {

        ApiResponse apiResponse = actorService.addActor(actor, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * Update actor by ID
     *
     * @param id    Integer
     * @param actor Actor
     * @param file  MultipartFile
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse updateActor(@PathVariable Integer id,
                                   @RequestPart(name = "json") Actor actor,
                                   @RequestPart(name = "file") MultipartFile file) throws IOException {
        return actorService.updateActor(id, actor, file);
    }

    /**
     * DELETE ACTOR BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteActor(@PathVariable Integer id) {
        return actorService.deleteActor(id);
    }
}