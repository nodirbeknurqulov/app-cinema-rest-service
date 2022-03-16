package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.AttachmentService;

import java.io.IOException;
import java.util.UUID;

// Nurkulov Nodirbek 3/15/2022  10:13 PM
@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @GetMapping
    public HttpEntity<?> getAllAttachment() {
        ApiResponse apiResponse = attachmentService.getAllAttachments();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAttachment(@PathVariable Integer id) {
        ApiResponse apiResponse = attachmentService.getAttachmentById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/download/{attachmentId}")
    public HttpEntity<?> getAttachmentFile(@PathVariable Integer attachmentId) throws IOException {
        return attachmentService.fileDownload(attachmentId);
    }

    @PostMapping
    public HttpEntity<?> fileUpload(@RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.fileUpload(file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAttachment(@PathVariable Integer id, @RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.editAttachment(id, file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable Integer id) {
        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
