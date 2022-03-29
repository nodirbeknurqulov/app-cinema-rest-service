package uz.pdp.appcinemarestservice.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "actors")
public class Actor extends AbsEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

//    @Override
//    public String toString() {
//        return "Actor{" +
//                "fullName='" + fullName + '\'' +
//                ", bio='" + bio + '\'' +
//                ", attachment=" + attachment.getName() +
//                '}';
//    }
}
