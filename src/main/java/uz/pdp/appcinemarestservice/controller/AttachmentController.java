package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

// Nurkulov Nodirbek 3/15/2022  10:13 PM

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/uploadFile")
    public HttpEntity<?> uploadFileToDb(MultipartHttpServletRequest request) throws IOException {
        ApiResponse apiResponse = attachmentService.uploadFile(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/downloadFile/{id}")
    public HttpEntity<?> downloadFile(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        ApiResponse apiResponse = attachmentService.downloadFile(response, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
