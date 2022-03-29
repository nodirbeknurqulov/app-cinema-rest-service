package uz.pdp.appcinemarestservice.service;

// Nurkulov Nodirbek 3/16/2022  7:20 AM

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.ActorRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {

    private final ActorRepository actorRepo;
    private final AttachmentService attachmentService;

    /**
     * GET ALL ACTORS BY PAGEABLE
     *
     * @param page INT
     * @param size int
     * @return List
     */
    public List<Actor> getAllActors(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Actor> actorPage = actorRepo.findAll(pageable);
        return actorPage.getContent();
    }

    public ResponseEntity<?> getAllActor2(){
        List<Actor> allActors = actorRepo.findAll();
        return ResponseEntity.ok(new ApiResponse("Success",true,allActors));
    }

    /**
     * GET ACTOR BY ID
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getActorById(Integer id) {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        return optionalActor.map(actor -> new ApiResponse("Success", true, actor))
                .orElseGet(() -> new ApiResponse("Actor not found!", false));
    }

    /**
     * ADD ACTOR
     * @param actor Actor
     * @param file  Multipart
     * @return Apiresponse
     */
    public ApiResponse addActor(Actor actor, MultipartFile file) throws IOException {

        Attachment attachment = attachmentService.uploadFile(file);
        if (attachment == null) {
            return new ApiResponse("Error!", false);
        }

        Actor newActor = new Actor();
        newActor.setAttachment(attachment);
        newActor.setBio(actor.getBio());
        newActor.setFullName(actor.getFullName());
        Actor save = actorRepo.save(newActor);
        return new ApiResponse("Actor added!!!", true, save);
    }

    /**
     * UPDATE ACTOR BY ID
     * @param id    Integer
     * @param actor Actor
     * @param file  Multipart
     * @return ApiResponse
     */
    public ApiResponse updateActor(Integer id, Actor actor, MultipartFile file) throws IOException {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!", false);
        }
        Actor updatingActor = optionalActor.get();
        updatingActor.setFullName(actor.getFullName());
        updatingActor.setBio(actor.getBio());

        Attachment attachment = attachmentService.uploadFile(file);
        updatingActor.setAttachment(attachment);
        Actor savedActor = actorRepo.save(updatingActor);
        return new ApiResponse("Actor updated!", true,savedActor);
    }

    /**
     * DELETE ACTOR BY ID
     * @param id Integer
     * @return ApiResponse
     * BU METHOD TOLIQ ISHLAMAYAPTI ACTORNI OCHIRAYAPTI
     * LEKIN UNING ATTACHMENTINI OCHIRMAYAPTI
     */
    public ApiResponse deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (!optionalActor.isPresent()){
            return new ApiResponse("Actor not found!!!", false);
        }
        Actor actor = optionalActor.get();
        actorRepo.delete(actor);
        return new ApiResponse("Actor deleted!!!", true);
    }
}
