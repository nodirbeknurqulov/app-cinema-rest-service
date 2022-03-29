package uz.pdp.appcinemarestservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;

// Nurkulov Nodirbek 3/15/2022  7:11 AM

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "attachments")
public class Attachment extends AbsEntity {
    private String originalFileName;

    private long size;

    private String contentType;

    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "attachment",cascade = CascadeType.ALL)
    private AttachmentContent attachmentContent;

//    @Override
//    public String toString() {
//        return "Attachment{" +
//                "originalFileName='" + originalFileName + '\'' +
//                ", size=" + size +
//                ", contentType='" + contentType + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
}

