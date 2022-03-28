package uz.pdp.appcinemarestservice.service;

// Nurkulov Nodirbek 3/16/2022  7:20 AM

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.ActorRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {

    private final AttachmentRepository attachmentRepo;
    private final AttachmentContentRepository attachmentContentRepo;
    private final ActorRepository actorRepo;

    public ApiResponse getAllActors() {
        List<Actor> all = actorRepo.findAll();
        if (all.size() != 0) {
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse addActor(Actor actor, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setOriginalFileName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment saveAttachment = attachmentRepo.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setData(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepo.save(attachmentContent);

            Actor actor1 = new Actor();
            actor1.setFullName(actor.getFullName());
            actor1.setAttachment(saveAttachment);
            Actor save = actorRepo.save(actor1);
            return new ApiResponse("Success", true, save);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse updateActor(Integer id, Actor actor, MultipartFile file) throws IOException {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor1 = optionalActor.get();

            Attachment attachment = actor1.getAttachment();
            Optional<Attachment> optionalAttachment = attachmentRepo.findById(attachment.getId());
            if (optionalAttachment.isPresent()) {
                Attachment attachment1 = optionalAttachment.get();
                Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(attachment.getId());
                if (optionalAttachmentContent.isPresent()) {
                    AttachmentContent attachmentContent = optionalAttachmentContent.get();
                    if (file != null) {
                        String originalFilename1 = file.getOriginalFilename();
                        long size1 = file.getSize();
                        String contentType1 = file.getContentType();
                        byte[] bytes = file.getBytes();

                        attachment1.setOriginalFileName(originalFilename1);
                        attachment1.setSize(size1);
                        attachment1.setContentType(contentType1);

                        attachmentContent.setData(bytes);
                        attachmentContent.setAttachment(attachment1);

                        actor1.setFullName(actor.getFullName());
                        actor1.setAttachment(attachmentRepo.save(attachment1));
                        Actor save = actorRepo.save(actor1);
                        return new ApiResponse("success", true, save);
                    }
                }
            }
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            attachmentRepo.delete(actor.getAttachment());
            actorRepo.delete(actor);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Not found", false);
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
