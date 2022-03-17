package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 3/15/2022  12:07 PM

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
