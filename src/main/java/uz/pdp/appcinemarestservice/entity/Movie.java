package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;
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

    @OneToOne
    private Attachment coverImage;

    @Column(nullable = false)
    private String trailerVideoUrl; // ex. youtube link

    @Column(nullable = false)
    private Date releaseDate;

    @Column(nullable = false)
    private double minPrice;

    @ManyToOne
    private Distributor distributor;

    @ManyToOne
    private Director director;

    private Double budget;

    @Column(nullable = false)
    private double distributorShareInPercentages;

    @OneToMany
    private List<Actor> actors;

    @ManyToMany
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieSchedule> movieSchedules;

}
