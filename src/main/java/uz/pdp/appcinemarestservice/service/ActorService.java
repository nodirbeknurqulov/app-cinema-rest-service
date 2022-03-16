package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/16/2022  7:20 AM

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.ActorRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {


    private final ActorRepository actorRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse getAllActors() {
        List<Actor> actorList = actorRepository.findAll();
        if (actorList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, actorList);
    }

    public ApiResponse getActor(Integer id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isPresent()) {
            return new ApiResponse("Distributor not found!", false);
        }
        return new ApiResponse("Success!", true, optionalActor.get());
    }

    public ApiResponse addActor(MultipartFile file, Actor actor) {
        try {
            Attachment attachment = attachmentRepository.save(new Attachment(file.getContentType(), file.getSize(), file.getOriginalFilename()));
            AttachmentContent attachmentContent = attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
            Actor newActor = new Actor(actor.getFullName(), actor.getBio(), attachment);
            Actor savedActor = actorRepository.save(newActor);
            return new ApiResponse("Successfully added!", true, savedActor);
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse("Error!!", false);
        }
    }

    public ApiResponse editActor(Integer id, MultipartFile file, Actor actor) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!!", false);
        }
        try {
            Actor editingActor = optionalActor.get();
            editingActor.setBio(actor.getBio());
            editingActor.setFullName(actor.getFullName());
            if (file.isEmpty()) {
                Actor saveActor = actorRepository.save(editingActor);
                return new ApiResponse("Successfully edited!", true, saveActor);
            }
            Attachment attachment = editingActor.getAttachment();
            attachment.setContentType(file.getContentType());
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            Attachment saveAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(saveAttachment.getId());
            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepository.save(attachmentContent);
            actor.setAttachment(saveAttachment);
            Actor saveActor = actorRepository.save(editingActor);
            return new ApiResponse("Successfully edited!", true, saveActor);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }

    public ApiResponse deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!!", false);
        }
        try {
            Actor actor = optionalActor.get();
            Attachment attachment = actor.getAttachment();
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            attachmentContentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(attachment.getId());
            actorRepository.deleteById(actor.getId());
            return new ApiResponse("Successfully deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }
}
