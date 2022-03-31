package uz.pdp.appcinemarestservice.projection;

import java.time.LocalTime;
import java.util.UUID;

public interface SessionTimeProjection {

    Integer getId();

    LocalTime getTime();

    Integer getSessionId();

}
