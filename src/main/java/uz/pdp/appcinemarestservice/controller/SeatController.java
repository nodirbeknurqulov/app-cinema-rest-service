package uz.pdp.appcinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.service.SeatService;

// Nurkulov Nodirbek 4/4/2022  2:25 PM
@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    SeatService service;

    @GetMapping("/available-seats/{movieSessionId}")
    public HttpEntity<?> getAvailableSeatsBySessionId(@PathVariable Integer movieSessionId){
        return service.getAvailableSeatsBySessionId(movieSessionId);
    }
}
