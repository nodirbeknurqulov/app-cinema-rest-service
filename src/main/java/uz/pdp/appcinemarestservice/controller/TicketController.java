package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/31/2022  11:18 AM

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.entity.User;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.UserRepository;
import uz.pdp.appcinemarestservice.service.TicketService;

import java.util.UUID;
@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final UserRepository userRepository;
    private final TicketService ticketService;

    @GetMapping
    public HttpEntity<?> getCurrentUserTickets() {
        User currentUser = userRepository.findByUsername("test");
        return ticketService.getCurrentUserTickets(currentUser.getId());
    }

    @GetMapping("/{ticketId}")
    public HttpEntity<?> purchaseTicket(@PathVariable Integer ticketId) {
        ApiResponse apiResponse = ticketService.purchaseTicket(ticketId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }
}
