package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/17/2022  7:43 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Photo extends AbsEntity {
    @ManyToOne
    private Movie movie;

    @OneToOne
    private Attachment attachment;
}
