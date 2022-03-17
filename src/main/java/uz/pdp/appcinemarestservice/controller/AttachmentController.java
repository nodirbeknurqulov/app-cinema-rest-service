package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

// Nurkulov Nodirbek 3/15/2022  10:13 PM

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/uploadFile")
    public HttpEntity<?> uploadFileToDb(MultipartHttpServletRequest request) throws IOException {
        ApiResponse apiResponse = attachmentService.uploadFile(request);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/downloadFile/{id}")
    public HttpEntity<?> downloadFile(HttpServletResponse response, @PathVariable Integer id){
        ApiResponse apiResponse = attachmentService.downloadFile(response, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }




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

















//    @GetMapping("/download/{attachmentId}")
//    public HttpEntity<?> getAttachmentFile(@PathVariable Integer attachmentId) throws IOException {
//        return attachmentService.fileDownload(attachmentId);
//    }
//


//
//    @GetMapping
//    public HttpEntity<?> getAllAttachments() {
//        ApiResponse apiResponse = attachmentService.getAllAttachments();
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
//    }
//
//    @GetMapping("/{id}")
//    public HttpEntity<?> getAttachment(@PathVariable Integer id) {
//        ApiResponse apiResponse = attachmentService.getAttachmentById(id);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }
//
//    @PutMapping("/{id}")
//    public HttpEntity<?> updateAttachment(@PathVariable Integer id, @RequestParam MultipartFile file) {
//        ApiResponse apiResponse = attachmentService.updateAttachment(id, file);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }
//
//    @DeleteMapping("/{id}")
//    public HttpEntity<?> deleteAttachment(@PathVariable Integer id) {
//        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
//    }
}
