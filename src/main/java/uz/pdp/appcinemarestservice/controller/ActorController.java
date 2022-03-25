package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/16/2022  7:21 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.payload.ActorDto;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.ActorRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;
import uz.pdp.appcinemarestservice.service.ActorService;

import java.io.IOException;
import java.util.UUID;
@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ApiResponse getActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public ApiResponse getActorById(@PathVariable Integer id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public ApiResponse addActor(@RequestPart("actor") Actor actor,
                                MultipartHttpServletRequest request) throws IOException {
        return actorService.addActor(actor, request);
    }

    @PutMapping("/{id}")
    public ApiResponse updateActor(@PathVariable Integer id,
                                   @RequestPart(name = "actor") Actor actor,
                                   @RequestPart(name = "file") MultipartFile file) throws IOException {
        return actorService.updateActor(id, actor, file);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteActor(@PathVariable Integer id){
        return actorService.deleteActor(id);
    }

}