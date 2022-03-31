package uz.pdp.appcinemarestservice.projection;
// Nurkulov Nodirbek 3/16/2022  12:32 PM

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appcinemarestservice.entity.Movie;

import java.sql.Date;
@Projection(types = {Movie.class})
public interface CustomMovie {
    Integer getId();

    String getTitle();

    Date getReleaseDate();

    Integer getCoverImgId();
}
