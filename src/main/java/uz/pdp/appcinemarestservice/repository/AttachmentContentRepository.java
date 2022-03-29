package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
   Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
//   AttachmentContent findByAttachmentId(Integer attachment_id);
}
