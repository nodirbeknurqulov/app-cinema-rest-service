package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 4/6/2022  8:56 AM

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.payload.UserDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public String login(@RequestBody UserDto userDto){
        // TODO: 4/6/2022
        return "";
    }
}
