package uz.pdp.appcinemarestservice.entity.attachements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;

// Nurkulov Nodirbek 3/15/2022  7:11 AM
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachments")
public class Attachment extends AbsEntity {
    private String originalFileName;

    private long size;

    private String contentType;

    private String name;
}
