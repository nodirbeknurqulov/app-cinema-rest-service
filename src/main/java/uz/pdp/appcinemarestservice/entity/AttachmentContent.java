package uz.pdp.appcinemarestservice.entity;

import lombok.*;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Arrays;

// Nurkulov Nodirbek 3/15/2022  7:11 AM
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_contents")
public class AttachmentContent extends AbsEntity {

    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

//    @Override
//    public String toString() {
//        return "AttachmentContent{" +
//                "data=" + Arrays.toString(data) +
//                '}';
//    }
}
