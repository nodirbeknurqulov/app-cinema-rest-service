package uz.pdp.appcinemarestservice.projection;
// Nurkulov Nodirbek 3/16/2022  12:32 PM

import java.sql.Date;

public interface CustomMovie {
    Integer getId();

    String getTitle();

    Date getReleaseDate();

    Integer getCoverImgId();
}
