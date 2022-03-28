package uz.pdp.appcinemarestservice.payload;

// Nurkulov Nodirbek 3/16/2022  12:01 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.*;
import uz.pdp.appcinemarestservice.entity.attachements.Attachment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class MovieDto {

    private String title;

    private String description;

    private int durationInMin;

    private MultipartFile request;

    private String trailerVideoUrl;

    private List<Integer> genreIds;

    private List<Integer> actorsId;

    private double minPrice;

    private Integer distributorId;

    private double distributorShareInPercentage;


//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private Integer durationInMinutes;
//
//    @OneToOne
//    private Attachment coverImage;
//
//    @Column(nullable = false)
//    private String trailerVideoUrl; // ex. youtube link
//
//    @Column(nullable = false)
//    private Date releaseDate;
//
//    @Column(nullable = false)
//    private Double minPrice;
//
//    @ManyToOne
//    private Distributor distributor;
//
//    @ManyToOne
//    private Director director;
//
//    private Double budget;
//
//    @Column(nullable = false)
//    private Double distributorShareInPercentages;
//
//    @ManyToMany
//    private List<Actor> actors;
//
//    @ManyToMany
//    private List<Genre> genres;
//
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<MovieSchedule> movieSchedules;
}
