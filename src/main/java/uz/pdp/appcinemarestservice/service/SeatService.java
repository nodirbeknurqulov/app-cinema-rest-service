package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 4/4/2022  2:27 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.projection.AvailableProjection;
import uz.pdp.appcinemarestservice.repository.SeatRepository;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public HttpEntity<?> getAvailableSeatsBySessionId(Integer id){
        List<AvailableProjection> availableSeatsBySessionId = seatRepository.findAvailableSeatsBySessionId(id);
        return ResponseEntity.ok(new ApiResponse("Success", true, availableSeatsBySessionId));
    }

}
