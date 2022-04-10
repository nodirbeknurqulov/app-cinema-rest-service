package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @OneToOne
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

    public Movie(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description, Integer durationInMinutes, Attachment coverImage, Attachment trailerVideo, LocalDate releaseDate, Double minPrice, Distributor distributor, Director director, Double budget, Double distributorShareInPercentages) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.coverImage = coverImage;
        this.trailerVideo = trailerVideo;
        this.releaseDate = releaseDate;
        this.minPrice = minPrice;
        this.distributor = distributor;
        this.director = director;
        this.budget = budget;
        this.distributorShareInPercentages = distributorShareInPercentages;
    }

    public Movie(String title, String description, Integer durationInMinutes, Attachment coverImage, Attachment trailerVideo, LocalDate releaseDate, Double minPrice, Distributor distributor, Director director, Double budget, Double distributorShareInPercentages) {
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.coverImage = coverImage;
        this.trailerVideo = trailerVideo;
        this.releaseDate = releaseDate;
        this.minPrice = minPrice;
        this.distributor = distributor;
        this.director = director;
        this.budget = budget;
        this.distributorShareInPercentages = distributorShareInPercentages;
    }
}
