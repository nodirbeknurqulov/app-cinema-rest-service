package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/16/2022  7:20 AM

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ActorDto;
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
//
//    /**
//     * ACTOR CRUDI HALI TUGAMADI DAVOM ETTIRISHIM KERAK
//     */
//
//    private final ActorRepository actorRepository;
//    private final AttachmentRepository attachmentRepository;
//    private final AttachmentContentRepository attachmentContentRepository;
//
//    public ApiResponse getAllActors() {
//        List<Actor> actorList = actorRepository.findAll();
//        if (actorList.size() == 0) {
//            return new ApiResponse("List empty!", false);
//        }
//        return new ApiResponse("Success", true, actorList);
//    }
//
//    public ApiResponse getActor(Integer id) {
//        Optional<Actor> optionalActor = actorRepository.findById(id);
//        if (optionalActor.isPresent()) {
//            return new ApiResponse("Distributor not found!", false);
//        }
//        return new ApiResponse("Success!", true, optionalActor.get());
//    }
//
//    public ApiResponse addActor(MultipartFile file, ActorDto actorDto) {
//
//        try {
//            Attachment attachment = new Attachment();
//            attachment.setSize(file.getSize());
//            attachment.setContentType(file.getContentType());
//            attachment.setFileOriginalName(attachment.getFileOriginalName());
//            Attachment savedAttachment = attachmentRepository.save(attachment);
//
//            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setAttachment(savedAttachment);
//            attachmentContent.setMainContent(file.getBytes());
//            attachmentContentRepository.save(attachmentContent);
//
//            Actor actor = new Actor();
//            actor.setAttachment(savedAttachment);
//            actor.setBio(actorDto.getBio());
//            actor.setFullName(actorDto.getFullName());
//            actorRepository.save(actor);
//
//            return new ApiResponse("Actor added!",true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ApiResponse("Error!!!",false);
//    }
//
//    public ApiResponse editActor(Integer id, MultipartFile file, Actor actor) {
//        Optional<Actor> optionalActor = actorRepository.findById(id);
//        if (!optionalActor.isPresent()) {
//            return new ApiResponse("Actor not found!!", false);
//        }
//        try {
//            Actor editingActor = optionalActor.get();
//            editingActor.setBio(actor.getBio());
//            editingActor.setFullName(actor.getFullName());
//            if (file.isEmpty()) {
//                Actor saveActor = actorRepository.save(editingActor);
//                return new ApiResponse("Successfully edited!", true, saveActor);
//            }
//            Attachment attachment = editingActor.getAttachment();
//            attachment.setContentType(file.getContentType());
//            attachment.setFileOriginalName(file.getOriginalFilename());
//            attachment.setSize(file.getSize());
//            Attachment saveAttachment = attachmentRepository.save(attachment);
//
//            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(saveAttachment.getId());
//            attachmentContent.setMainContent(file.getBytes());
//            attachmentContent.setAttachment(saveAttachment);
//            attachmentContentRepository.save(attachmentContent);
//            actor.setAttachment(saveAttachment);
//            Actor saveActor = actorRepository.save(editingActor);
//            return new ApiResponse("Successfully edited!", true, saveActor);
//        } catch (Exception e) {
//            return new ApiResponse("Error!!", false);
//        }
//    }
//
//    public ApiResponse deleteActor(Integer id) {
//        Optional<Actor> optionalActor = actorRepository.findById(id);
//        if (!optionalActor.isPresent()) {
//            return new ApiResponse("Actor not found!!", false);
//        }
//        try {
//            Actor actor = optionalActor.get();
//            Attachment attachment = actor.getAttachment();
//            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
//            attachmentContentRepository.deleteById(attachmentContent.getId());
//            attachmentRepository.deleteById(attachment.getId());
//            actorRepository.deleteById(actor.getId());
//            return new ApiResponse("Successfully deleted!", true);
//        } catch (Exception e) {
//            return new ApiResponse("Error!!", false);
//        }
//    }
}
