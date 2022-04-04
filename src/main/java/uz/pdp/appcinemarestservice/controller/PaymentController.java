package uz.pdp.appcinemarestservice.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.projection.TicketProjection;
import uz.pdp.appcinemarestservice.repository.PaymentTypeRepository;
import uz.pdp.appcinemarestservice.repository.TicketRepository;
import uz.pdp.appcinemarestservice.repository.TransactionHistoryRepository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    PaymentTypeRepository payTypeRepo;

    @Value("${STRIPE_KEY}")
    String stripeApiKey;

    @GetMapping("/purchase")
    public HttpEntity<?> purchaseTicket() throws StripeException {
        Integer userId = 1;
        List<TicketProjection> ticketByUserId = ticketRepository.getTicketByUserId(userId);
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        Stripe.apiKey = stripeApiKey;

        for (TicketProjection ticketProjection : ticketByUserId) {
            double price = ticketProjection.getPrice();
            Double finalPrice = (price + 0.3) / (1 - 0.029) * 100;
            System.out.println(ticketProjection.getPrice());
            System.out.println(ticketProjection.getMovieTitle());

            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(ticketProjection.getMovieTitle())
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount(finalPrice.longValue())
                    .build();

//        Stripedagi ticket malumotlarini
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();

            lineItems.add(lineItem);
        }

        // current data
        SessionCreateParams params = SessionCreateParams
                .builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(lineItems)
                .setClientReferenceId("1")
                .setCancelUrl("http://localhost:8080/payment-failed")
                .setSuccessUrl("http://localhost:8080/success")
                .build();
        Session session = Session.create(params);

        String url = session.getUrl();
        return ResponseEntity.ok(url);
    }


    @PostMapping("/refund")
    public HttpEntity<?> refundTicket(@RequestBody List<Integer> ticketIds) throws Exception {
        Stripe.apiKey = stripeApiKey;

        Integer userId = 1;
        List<Ticket> ticketList = ticketRepository.findAllById(ticketIds);

        for (Ticket ticket : ticketList) {
            if (ticket.getStatus().equals(TicketStatus.PURCHASED)) {
                if (ticket.getUser().getId() == userId) {
                    TransactionHistory transactionHistory = transactionHistoryRepository.getByTicketId(ticket.getId());
                    double price = ticket.getPrice() * 100;

                    RefundCreateParams params = RefundCreateParams.builder()
                            .setPaymentIntent(transactionHistory.getPaymentIntent())
                            .setAmount((long) price)
                            .build();

                    try {
                        Refund refund = Refund.create(params);

                        if (refund.getStatus().equals("succeeded")) {
                            ticket.setStatus(TicketStatus.REFUNDED);
                            ticketRepository.save(ticket);
                            transactionHistoryRepository.save(new TransactionHistory(ticketList,
                                    refund.getAmount().doubleValue(), true, payTypeRepo.findById(1).get(),
                                    refund.getPaymentIntent()));
                        }
                    } catch (StripeException e) {
                        e.printStackTrace();
                    }
                }
            } else throw new Exception();

        }
        return ResponseEntity.ok("Successeded");
    }
}
