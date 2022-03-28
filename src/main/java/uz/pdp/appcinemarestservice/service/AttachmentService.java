package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

// Nurkulov Nodirbek 3/15/2022  10:33 PM

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private static final String uploadDirectory = "downloads";

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
            attachment.setOriginalFileName(originalFilename);
            attachment.setSize(size);
            Attachment savedAttachment = attachmentRepository.save(attachment);


            assert originalFilename != null;
            String[] split = originalFilename.split("\\.");

            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];

            attachment.setName(name);
            attachmentRepository.save(attachment);

            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);

            //fileni byte arraylarini saqlaymiz
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setData(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("File uploaded!!!", true);
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse downloadFile(HttpServletResponse response, Integer id) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\""
                            + attachment.getOriginalFileName() + "\"");
            response.setContentType(attachment.getContentType());

            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream()); //outputstream oqish uchun kk fileni
            return new ApiResponse("File downloaded!", true);
        }
        return new ApiResponse("Error!", false);
    }

    public Attachment uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        Attachment attachment = new Attachment();
        attachment.setOriginalFileName(originalFilename);
        attachment.setSize(size);
        attachment.setContentType(contentType);
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setData(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return savedAttachment;
    }



}
