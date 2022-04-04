package uz.pdp.appcinemarestservice.projection;

public interface AvailableProjection {

        Integer getId();

        Integer getSeatNumber();

        boolean isAvailable();

        Integer getRowNumber();
}
