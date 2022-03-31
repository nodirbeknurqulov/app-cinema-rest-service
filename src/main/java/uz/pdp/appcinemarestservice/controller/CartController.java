package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.User;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.TicketDto;
import uz.pdp.appcinemarestservice.repository.UserRepository;
import uz.pdp.appcinemarestservice.service.CartService;


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public HttpEntity<?> showTicketsInTheCart(@PathVariable Integer userId) {
        ApiResponse apiResponse = cartService.getTicketInTheCart(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addTicketInToCart(@RequestBody TicketDto ticketDto) {
        //KEYINCHALIK @CURRENTUSER GA O'ZGARADI
        User currentUser = userRepository.findByUsername("test");

        ApiResponse apiResponse = cartService.addToCart(currentUser.getId(), ticketDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @DeleteMapping("/{userId}/{ticketId}")
    public HttpEntity<?> deleteTicketFromCart(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        ApiResponse apiResponse = cartService.deleteTicketFromCart(userId, ticketId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @DeleteMapping("/clear/{userId}")
    public HttpEntity<?> clearCart(@PathVariable Integer userId) {
        ApiResponse apiResponse = cartService.clearCart(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }
}
