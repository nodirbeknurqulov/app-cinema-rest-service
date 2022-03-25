package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/16/2022  7:36 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.attachements.AttachmentContent;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "directors")
public class Director extends AbsEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    private AttachmentContent photo;
}
