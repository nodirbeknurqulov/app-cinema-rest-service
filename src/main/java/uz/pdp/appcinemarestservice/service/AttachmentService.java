package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import javax.transaction.Transactional;
import java.util.List;

// Nurkulov Nodirbek 3/15/2022  10:33 PM
@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public ApiResponse getAllAttachments() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        if (attachmentList.size() != 0) {
            return new ApiResponse("Success", true, attachmentList);
        }
        return new ApiResponse("Attachment list is empty!!!", false);
    }
}
