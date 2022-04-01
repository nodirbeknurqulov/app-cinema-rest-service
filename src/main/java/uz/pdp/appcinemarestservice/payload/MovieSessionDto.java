package uz.pdp.appcinemarestservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieSessionDto {

    private Integer movieAnnouncementId;
    private List<ReservedHallDto> reservedHallDtoList;
}
