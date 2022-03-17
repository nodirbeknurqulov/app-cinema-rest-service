package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/15/2022  10:33 PM

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;


//
//    public ResponseEntity<ByteArrayResource> fileDownload(Integer attachmentId) throws IOException{
//        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent.getAttachment().getFileOriginalName() + "\"")
//                .body(new ByteArrayResource(attachmentContent.getMainContent()));
//    }

    public ApiResponse uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {

            //file haqidagi ma'lumotni olamiz
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();

            //va attachmentga saqlaymiz
            Attachment attachment = new Attachment();
            attachment.setContentType(contentType);
//            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            //fileni byte arraylarini saqlaymiz
            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("File uploaded!!!", true);
        }
        return new ApiResponse("Error", false);
    }


    public ApiResponse downloadFile(HttpServletResponse response, Integer id){
//    @GetMapping("/downloadFile/{id}")
//    public void downloadFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
//            if (contentOptional.isPresent()) {
//                AttachmentContent attachmentContent = contentOptional.get();//shuni ichida byte lar turibdi
//
//                //bu fileni nomini bervorish uchun kerak
//                response.setHeader("Content-Disposition",
//                        "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
//
//                //bu esa fileni content typeini berish uchun klientga
//                response.setContentType(attachment.getContentType());
//
//                //bu esa fileni asosiy contentini berish uchun
//                FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream()); //outputstream oqish uchun kk fileni
//            }
//        }
//    }
        return null;
    }

//    public ApiResponse getAllAttachments() {
//        List<Attachment> attachmentList = attachmentRepository.findAll();
//        if (attachmentList.size() != 0) {
//            return new ApiResponse("Success", true, attachmentList);
//        }
//        return new ApiResponse("List empty!", false);
//
//    }
//
//    public ApiResponse getAttachmentById(Integer id) {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            return new ApiResponse("Attachment not found!", false);
//        }
//        return new ApiResponse("Success!", true, optionalAttachment.get());
//
//    }
//
//    public ApiResponse updateAttachment(Integer id, MultipartFile file) {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (!optionalAttachment.isPresent()) {
//            return new ApiResponse("Attachment not found!", false);
//        }
//        try {
//            Attachment editingAttachment = optionalAttachment.get();
//            editingAttachment.setContentType(file.getContentType());
//            editingAttachment.setFileOriginalName(file.getOriginalFilename());
//            editingAttachment.setSize(file.getSize());
//            Attachment attachment = attachmentRepository.save(editingAttachment);
//
//            AttachmentContent editingAttachmentContent = attachmentContentRepository.getById(editingAttachment.getId());
//            editingAttachmentContent.setAttachment(attachment);
//            editingAttachmentContent.setMainContent(file.getBytes());
//            return new ApiResponse("Successfully edited!!", true);
//        } catch (Exception e) {
//            return new ApiResponse("Error!!!", false);
//        }
//    }
//
//    public ApiResponse deleteAttachment(Integer id) {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (!optionalAttachment.isPresent()) {
//            return new ApiResponse("Attachment not found!", false);
//        }
//
//        AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
//        attachmentContentRepository.deleteById(attachmentContent.getId());
//        attachmentRepository.deleteById(id);
//        return new ApiResponse("Successfully deleted!!", true);
//    }

}
