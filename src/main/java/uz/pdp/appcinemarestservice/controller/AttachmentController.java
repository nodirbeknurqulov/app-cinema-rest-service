package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.AttachmentService;

// Nurkulov Nodirbek 3/15/2022  10:13 PM
@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAttachments() {
        ApiResponse allAttachments = attachmentService.getAllAttachments();
        return ResponseEntity.status(allAttachments.isSuccess() ? 200 : 204).body(allAttachments);
    }
}
