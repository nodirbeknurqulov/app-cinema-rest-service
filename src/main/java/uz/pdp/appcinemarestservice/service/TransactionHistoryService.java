package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.repository.TicketRepository;
import uz.pdp.appcinemarestservice.repository.TransactionHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

// Nurkulov Nodirbek 4/1/2022  9:53 AM
@Service
@RequiredArgsConstructor
public class TransactionHistoryService {

    private final TicketRepository ticketRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public void addTransactionHistory(Integer userId, String paymentIntent) {
        List<Ticket> ticketList = ticketRepository.findByUserIdAndStatus(userId, TicketStatus.NEW);

        Double totalAmount = ticketList.stream().map(Ticket::getPrice).
                collect(Collectors.toList()).stream().mapToDouble(value -> value).sum();

        boolean isRefunded;
        TransactionHistory transactionHistory = new TransactionHistory(
                ticketList,
                totalAmount,
                false,
                null,
                paymentIntent);
        transactionHistoryRepository.save(transactionHistory);
    }

    public boolean changeTicketStatusToPurchase(Integer userId) {
        try {
            List<Ticket> ticketList = ticketRepository.findByUserIdAndStatus(userId, TicketStatus.NEW);
            for (Ticket ticket : ticketList) {
                ticket.setStatus(TicketStatus.PURCHASED);
            }
            ticketRepository.saveAll(ticketList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
