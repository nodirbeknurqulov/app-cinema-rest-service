package uz.pdp.appcinemarestservice.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.repository.TransactionHistoryRepository;
import uz.pdp.appcinemarestservice.service.TicketService;
import uz.pdp.appcinemarestservice.service.TransactionHistoryService;

// Nurkulov Nodirbek 3/31/2022  9:27 AM

@RestController
@RequestMapping("/api/stripe-webhook")
@RequiredArgsConstructor
public class StripeEventController {

    private final TicketService ticketService;
    private final TransactionHistoryService transactionHistoryService;

    @Value("${STRIPE_KEY}")
    String stripeApiKey;

    @Value("${WEBHOOK_SECRET_KEY}")
    String endpointSecret;

    @PostMapping
    public Object handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String sigHeader) {
        Stripe.apiKey = stripeApiKey;
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        }

        assert event != null;
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
            fullFillOrder(session);
        }

        System.out.println("Got payload: " + payload);
        return "";
    }

    public void fullFillOrder(Session session) {
        System.out.println("Current user's id: " + session.getClientReferenceId());
        String currentUserId = session.getClientReferenceId();
        transactionHistoryService.addTransactionHistory(Integer.parseInt(currentUserId), session.getPaymentIntent());
        ticketService.changeTicketStatusToPurchase(Integer.parseInt(currentUserId));
    }
}
