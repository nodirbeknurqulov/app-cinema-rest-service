package uz.pdp.appcinemarestservice.service;

// Nurkulov Nodirbek 3/16/2022  7:20 AM

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.ActorRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {

    private final AttachmentRepository attachmentRepo;
    private final AttachmentContentRepository attachmentContentRepo;
    private final ActorRepository actorRepo;
    private final AttachmentService attachmentService;

//    public ApiResponse getAllActors() {
//        List<Actor> all = actorRepo.findAll();
//        if (all.size() != 0) {
//            return new ApiResponse("Success", true, all);
//        }
//        return new ApiResponse("Not found", false, null);
//    }

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


    /**
     * ADD ACTOR
     *
     * @param actor Actor
     * @param file  Multipart
     * @return Apiresponse
     * @throws IOException throw
     */
    public ApiResponse addActor(Actor actor, MultipartFile file) throws IOException {

        boolean exists = actorRepo.existsByFullNameAndId(actor.getFullName(), actor.getId());
        if (exists)
            return new ApiResponse("This actor is already exist!!!");

        Attachment attachment = attachmentService.uploadFile(file);
        if (attachment == null) {
            return new ApiResponse("Error for uploading!!!", false);
        }

        Actor newActor = new Actor();
        newActor.setAttachment(attachment);
        newActor.setBio(actor.getBio());
        newActor.setFullName(actor.getFullName());
        actorRepo.save(newActor);
        return new ApiResponse("Actor added!!!", true);
    }


//    public ApiResponse updateActor(Integer id, Actor actor, MultipartFile file) throws IOException {
//        Optional<Actor> optionalActor = actorRepo.findById(id);
//        if (optionalActor.isPresent()) {
//            Actor actor1 = optionalActor.get();
//
//            Attachment attachment = actor1.getAttachment();
//            Optional<Attachment> optionalAttachment = attachmentRepo.findById(attachment.getId());
//            if (optionalAttachment.isPresent()) {
//                Attachment attachment1 = optionalAttachment.get();
////                Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(attachment.getId());
//                if (optionalAttachmentContent.isPresent()) {
//                    AttachmentContent attachmentContent = optionalAttachmentContent.get();
//                    if (file != null) {
//                        String originalFilename1 = file.getOriginalFilename();
//                        long size1 = file.getSize();
//                        String contentType1 = file.getContentType();
//                        byte[] bytes = file.getBytes();
//
//                        attachment1.setOriginalFileName(originalFilename1);
//                        attachment1.setSize(size1);
//                        attachment1.setContentType(contentType1);
//
//                        attachmentContent.setData(bytes);
//                        attachmentContent.setAttachment(attachment1);
//
//                        actor1.setFullName(actor.getFullName());
//                        actor1.setAttachment(attachmentRepo.save(attachment1));
//                        Actor save = actorRepo.save(actor1);
//                        return new ApiResponse("success", true, save);
//                    }
//                }
//            }
//        }
//        return new ApiResponse("Not found", false, null);
//    }


    /**
     * DELETE ACTOR BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (!optionalActor.isPresent()) return new ApiResponse("Actor not found!!!", false);
        Actor actor = optionalActor.get();
//
//        Optional<Attachment> optionalAttachment = attachmentRepo.findById(actor.getAttachment().getId());
//        if (!optionalAttachment.isPresent()) return new ApiResponse("Attachment not found!", false);

//        Attachment attachment = optionalAttachment.get();
        actorRepo.delete(actor);
//        attachmentRepo.delete(attachment);
        return new ApiResponse("Actor deleted!!!", true);

//            Attachment attachment = actor.getAttachment();
//            AttachmentContent attachmentContent = attachmentContentRepo.findByAttachmentId(attachment.getId())

    }

    public ApiResponse getActorById(Integer id) {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            return new ApiResponse("Success", true, actor);
        }
        return new ApiResponse("Not found", false, null);
    }

}
