package uz.pdp.appcinemarestservice.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.PaymentType;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.repository.PaymentTypeRepository;
import uz.pdp.appcinemarestservice.repository.TicketRepository;
import uz.pdp.appcinemarestservice.repository.TransactionHistoryRepository;
import uz.pdp.appcinemarestservice.service.TicketService;
import uz.pdp.appcinemarestservice.service.TransactionHistoryService;

import java.util.List;

// Nurkulov Nodirbek 3/31/2022  9:27 AM

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StripeEventController {

    private final TicketService ticketService;
    private final TransactionHistoryService transactionHistoryService;

    @Value("${STRIPE_KEY}")
    String stripeApiKey;

    @Value("${WEBHOOK_SECRET_KEY}")
    String endpointSecret;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    @PostMapping("/webhook")
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
        List<Ticket> ticketList = ticketRepository.findByUserIdAndStatus(Integer.parseInt(session.getClientReferenceId()), TicketStatus.NEW);
        for (Ticket ticket : ticketList) {
            ticket.setStatus(TicketStatus.PURCHASED);
            ticketRepository.save(ticket);
        }

        transactionHistoryRepository.save(new TransactionHistory(ticketList, session.getAmountTotal().doubleValue(), false, paymentTypeRepository.findById(1).get(), session.getPaymentIntent()));

    }
}
