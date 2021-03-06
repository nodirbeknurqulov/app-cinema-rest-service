package uz.pdp.appcinemarestservice.payload;

// Nurkulov Nodirbek 3/16/2022  12:01 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    @NotNull(message = "Movie title can't be empty!")
    private String title;

    @NotNull(message = "Movie description can't be empty!")
    private String description;

    @NotNull(message = "Movie duration can't be empty!")
    private int durationInMin;

    @NotNull(message = "Movie budget can't be empty!")
    private Double budget;

    @NotNull(message = "Movie release date can't be empty!")
    private LocalDate releaseDate;

    @NotNull(message = "Movie trailer video can't be empty")
    private String trailerVideoUrl;

    @NotNull(message = "Movie minimum price can't be empty!")
    private double minPrice;

    @NotNull(message = "Movie distributor id can't be empty!")
    private Integer distributorId;

    @NotNull(message = "Movie release date can't be empty!")
    private double distributorShareInPercentage;

    private List<Integer> genreIds = new ArrayList<>();

    private List<Integer> actorsId = new ArrayList<>();
}
