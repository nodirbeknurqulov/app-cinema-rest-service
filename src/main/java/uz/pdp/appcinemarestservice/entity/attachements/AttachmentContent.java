package uz.pdp.appcinemarestservice.entity.attachements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

// Nurkulov Nodirbek 3/15/2022  7:11 AM
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_contents")
public class AttachmentContent extends AbsEntity {

    private byte[] data;

    @OneToOne
    private Attachment attachment;
}
