package uz.pdp.appcinemarestservice.payload;
// Nurkulov Nodirbek 3/16/2022  12:01 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Genre;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private Integer id;

    private String title;

    private String description;

    private int durationInMin;

    private double minPrice;

    private UUID coverImgId;

    private String trailerVideoUrl; // ex. youtube link

    private Date releaseDate;

    private Double budget;

    private Distributor distributor;

    private Double distributorShareInPercentage;

    private List<Genre> genres;

}
