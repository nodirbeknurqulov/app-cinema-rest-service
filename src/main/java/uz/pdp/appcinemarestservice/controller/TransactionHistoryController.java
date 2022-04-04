package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 4/1/2022  11:05 AM

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.entity.User;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.projection.TicketProjection;
import uz.pdp.appcinemarestservice.repository.TicketRepository;
import uz.pdp.appcinemarestservice.repository.TransactionHistoryRepository;
import uz.pdp.appcinemarestservice.repository.UserRepository;
import uz.pdp.appcinemarestservice.service.TicketService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionHistoryController {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

//    @GetMapping("/api/purchase")
//    public HttpEntity<?> createStripeSession() {
//
//        Stripe.apiKey = "sk_test_51Kiti6Ao7BOEJ2PVKqf0856wWii5NiROlYxBi9cHSLgQQBpsxbk9NROOjL5R1XZxlpwhil44EdSzDInxQb0MvsTv00PROPNSlY";
//        User currentUser = userRepository.findByUsername("test");
//        List<TicketProjection> allTickets = ticketRepository.getTicketByUserId(currentUser.getId());
//
//        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
//
//        for (TicketProjection ticket : allTickets) {
//            String movieTitle = ticket.getTitle();
//            Double ticketPrice = ticket.getPrice();
//
//            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
//                    .builder()
//                    .setName(movieTitle)
//                    .build();
//
//            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
//                    .builder()
//                    .setProductData(productData)
//                    .setCurrency("usd")
//                    .setUnitAmount((long) (ticketPrice * 100))
//                    .build();
//
//            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
//                    .builder()
//                    .setPriceData(priceData)
//                    .setQuantity(1L)
//                    .build();
//
//            lineItems.add(lineItem);
//        }
//
//        SessionCreateParams params = SessionCreateParams
//                .builder()
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setCancelUrl("http://localhost:8080/failed")
//                .setSuccessUrl("http://localhost:8080/success")
//                .addAllLineItem(lineItems)
//                .build();
//        try {
//            Session session = Session.create(params);
//            String checkoutUrl = session.getUrl();
//            return ResponseEntity.ok(checkoutUrl);
//
//        } catch (StripeException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().build();
//    }

//    public HttpEntity<?> refundTicket() {
//        String paymentIntent = transactionHistoryRepository.getPaymentIntentByTicketId(refundingTickets.get(0).getId());
//        RefundCreateParams params = RefundCreateParams
//                .builder()
//                .setPaymentIntent(paymentIntent)
//                .build();
//        try {
//            Refund refund = Refund.create(params);
//
//
//        } catch (StripeException e) {
//            return new ApiResponse("Something went wrong with Stripe server", false);
//        }
//        return ResponseEntity.ok("dffsg");
//    }
}
