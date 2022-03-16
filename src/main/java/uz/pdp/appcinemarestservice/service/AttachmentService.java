package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Nurkulov Nodirbek 3/15/2022  10:33 PM
@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse fileUpload(MultipartFile file) {
        try {
            Attachment savedAttachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType()));
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), savedAttachment));
            return new ApiResponse("Successfully uploaded!!!", true);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ResponseEntity<ByteArrayResource> fileDownload(Integer attachmentId) throws IOException{
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent.getAttachment().getFileOriginalName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getMainContent()));
    }

    public ApiResponse getAllAttachments() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        if (attachmentList.size() != 0) {
            return new ApiResponse("Success", true, attachmentList);
        }
        return new ApiResponse("List empty!", false);

    }

    public ApiResponse getAttachmentById(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        return new ApiResponse("Success!", true, optionalAttachment.get());

    }

    public ApiResponse editAttachment(Integer id, MultipartFile file) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        try {
            Attachment editingAttachment = optionalAttachment.get();
            editingAttachment.setContentType(file.getContentType());
            editingAttachment.setFileOriginalName(file.getOriginalFilename());
            editingAttachment.setSize(file.getSize());
            Attachment attachment = attachmentRepository.save(editingAttachment);

            AttachmentContent editingAttachmentContent = attachmentContentRepository.getById(editingAttachment.getId());
            editingAttachmentContent.setAttachment(attachment);
            editingAttachmentContent.setMainContent(file.getBytes());
            return new ApiResponse("Successfully edited!!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse deleteAttachment(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }

        AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new ApiResponse("Successfully deleted!!", true);
    }

}
