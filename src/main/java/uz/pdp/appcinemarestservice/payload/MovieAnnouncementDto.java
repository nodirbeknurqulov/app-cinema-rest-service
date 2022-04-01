package uz.pdp.appcinemarestservice.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class MovieAnnouncementDto {

    @NotNull(message = "MOVIE_ANNOUNCEMENT_REQUIRED")
    private Integer movieId;

    @NotBlank(message = "IS_ACTIVE_REQUIRED")
    private boolean isActive;

}
