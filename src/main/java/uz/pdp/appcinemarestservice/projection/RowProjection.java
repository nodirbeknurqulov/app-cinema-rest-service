package uz.pdp.appcinemarestservice.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appcinemarestservice.entity.Hall;
import uz.pdp.appcinemarestservice.entity.Row;

@Projection(types = {Row.class, Hall.class})
public interface RowProjection {

    Integer getId();

    Integer getNumber();

    String getName();
}
