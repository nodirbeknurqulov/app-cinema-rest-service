package uz.pdp.appcinemarestservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private Integer movieSessionId;

    private Integer seatId;
}
