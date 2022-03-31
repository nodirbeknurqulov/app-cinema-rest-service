package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Integer durationInMinutes;

    @OneToOne
    private Attachment coverImage;

    @OneToOne
    private Attachment trailerVideo; // ex. youtube link

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double minPrice;

    @ManyToOne
    private Distributor distributor;

    @ManyToOne
    private Director director;

    private Double budget;

    @Column(nullable = false)
    private Double distributorShareInPercentages;

    @ManyToMany
    private List<Actor> actors;

    @ManyToMany
    private List<Genre> genres;

    public Movie(String title, String description, Integer durationInMinutes, Double minPrice, Attachment coverImage, Attachment trailerVideo, LocalDate releaseDate, Double budget, Double distributorShareInPercent) {
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.minPrice = minPrice;
        this.coverImage = coverImage;
        this.trailerVideo = trailerVideo;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.distributorShareInPercentages = distributorShareInPercent;
    }
}
