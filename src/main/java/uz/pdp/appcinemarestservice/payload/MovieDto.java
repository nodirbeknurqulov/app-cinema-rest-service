package uz.pdp.appcinemarestservice.payload;

// Nurkulov Nodirbek 3/16/2022  12:01 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

    private String title;

    private String description;

    private int durationInMin;

    private MultipartHttpServletRequest request;

    private String trailerVideoUrl;

    private List<Integer> genreIds;

    private double minPrice;

    private Integer distributorId;

    private double distributorShareInPercentage;

    private List<Integer> actorsId;


//    private Integer coverImgId;
//
//    private Date releaseDate;
//
//    private Double budget;

}
