package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

// Nurkulov Nodirbek 3/15/2022  6:53 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "movies")
public class Movie extends AbsEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int durationInMinutes;

    @OneToMany
    private List<Attachment> coverImage;

    @Column(nullable = false)
    private double minPrice;

    @OneToOne
    private Distributor distributor;

    @Column(nullable = false)
    private double distributorShareInPercentages;

    @ManyToMany
    private List<Actor> actors;

    @ManyToMany
    private List<Attachment> attachment;
}
