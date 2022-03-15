package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

// Nurkulov Nodirbek 3/15/2022  7:11 AM
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_content")
public class AttachmentContent extends AbsEntity {

    private byte[] mainContent;//asosiy content(mag'zi)

    //select * from attachment_content where attachment_id=100
//    private Integer attachment_id;
    @OneToOne
    private Attachment attachment;//qaysi filega tegishli ekanligini bildiradi
}
