package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import uz.pdp.appcinemarestservice.entity.MovieSession;
import uz.pdp.appcinemarestservice.entity.Seat;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.User;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.TicketDto;
import uz.pdp.appcinemarestservice.projection.CustomTicket;
import uz.pdp.appcinemarestservice.projection.CustomTicketForCart;
import uz.pdp.appcinemarestservice.repository.SeatRepository;
import uz.pdp.appcinemarestservice.repository.SessionRepository;
import uz.pdp.appcinemarestservice.repository.TicketRepository;
import uz.pdp.appcinemarestservice.repository.UserRepository;


import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final SessionRepository sessionRepository;
    private final WaitingTimeRepository waitingTimeRepository;

    public ApiResponse addToCart(Integer userId, TicketDto ticketDto) {
        Integer movieSessionId = ticketDto.getMovieSessionId();
        Integer seatId = ticketDto.getSeatId();
        // TODO: 30.03.2022
//        CustomTicketForCart customTicketForCart = ticketRepository.checkSeatIfAvailable(movieSessionId, seatId);
//        if (customTicketForCart != null) {
//            return new ApiResponse("This seat is not available!!!", false);
//        }
        User user = userRepository.findById(userId).get();
        Seat seat = seatRepository.findById(seatId).get();
        MovieSession session = sessionRepository.getById(movieSessionId);
        Ticket newTicket = new Ticket(session, seat, null, getPriceOfSeat(seatId, movieSessionId), TicketStatus.NEW, user);
        ticketRepository.save(newTicket);
        Integer waitingMinute = waitingTimeRepository.getWaitingMinute();
        if (newTicket != null) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    CustomTicketForCart ticketForCart = ticketRepository.getTicketByIdForCart(newTicket.getId());
                    try {
                        if (ticketForCart.getStatus().equals(TicketStatus.NEW)) {
                            ticketRepository.deleteById(newTicket.getId());
                            System.out.println("Ticket is deleted!" + newTicket.getId());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            };
        scheduleDeleteTicket(newTicket);
        }
        return new ApiResponse("Successfully added to cart!", true);
    }

    public void scheduleDeleteTicket(Ticket ticket) {
        try {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Ticket ticketFromDb = ticketRepository.findById(ticket.getId()).orElseThrow(() ->
                            new ResourceAccessException("Ticket Not Found!!!"));
                    if (ticketFromDb.getStatus().equals(TicketStatus.NEW)) {
                        System.out.println(ticketFromDb.getId() + "Ticked deleted..." + new Date());
                        ticketRepository.delete(ticketFromDb);
                    }
                }
            };
            Timer timer = new Timer();
            long dalyTime = 300000000L;
//            long dalyTime = 300000;

            System.out.println("Scheduler started..." + new Date());
            System.out.println("Delay" + dalyTime);

            timer.schedule(timerTask, dalyTime);
        } catch (Exception e) {
            System.out.println("scheduleDeleteTicket method Error!!!");
            e.printStackTrace();
        }
    }

    public Double getPriceOfSeat(Integer seatId, Integer movieSessionId) {
        return seatRepository.getPriceOfSeatBySeatIdAndMovieSessionId(seatId, movieSessionId);
    }

    public ApiResponse getTicketInTheCart(Integer userId){
        List<CustomTicket> tickets = userRepository.getTicketsInTheCart(userId);
        if (!tickets.isEmpty()) {
            return new ApiResponse("Success!", true, tickets);
        }
        return new ApiResponse("Not Found!!!", false);
    }

    /**
     * CLEAR CART
     * @param userId Integer
     * @return ApiResponse
     */
    public ApiResponse clearCart(Integer userId){
        try {
            userRepository.clearCartOfUser(userId);
            return new ApiResponse("Success!", true);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }

    public  ApiResponse deleteTicketFromCart(Integer userId, Integer ticketId){
        try {
            userRepository.deleteTicketFromCart(userId, ticketId);
            return new ApiResponse("Success!", true);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }
}
