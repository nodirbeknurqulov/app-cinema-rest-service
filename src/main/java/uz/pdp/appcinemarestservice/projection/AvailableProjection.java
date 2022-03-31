package uz.pdp.appcinemarestservice.projection;

import java.util.UUID;

public interface AvailableProjection {

        Integer getId();

        Integer getSeatNumber();

        boolean isAvailable();

        Integer getRowNumber();
}
