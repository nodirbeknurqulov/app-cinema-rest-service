package uz.pdp.appcinemarestservice.entity;

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
    private String fileOriginalName;//pdp.jpg, inn.pdf

    private long size;//2048 KB

    private String contentType;// application/pdf || image/png

//    //bu 'File systemga' saqlaganda kerak boladi'
//    private String name;//papkani ichidan topish uchun
}
