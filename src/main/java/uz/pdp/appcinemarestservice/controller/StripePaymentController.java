package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/31/2022  8:51 AM

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripePaymentController {

    @RequestMapping("/success")
    public ResponseEntity<?> getSuccess() {
        return ResponseEntity.ok("To'lov amalga oshirildi");
    }
}
