package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  8:45 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "transaction_histories")
public class TransactionHistory extends AbsEntity {

        @ManyToMany
        @JoinTable(name = "transaction_histories_tickets",
                joinColumns =@JoinColumn(name = "transaction_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
        private List<Ticket> ticketList;

        private Double amount;

        private boolean isRefunded;

        private LocalDate date=LocalDate.now();

        @OneToOne
        private PaymentType payType;

        private String paymentIntent;

        public TransactionHistory(List<Ticket> ticketList, Double amount, boolean isRefunded, PaymentType payType, String paymentIntent) {
            this.ticketList = ticketList;
            this.amount = amount;
            this.isRefunded = isRefunded;
            this.payType = payType;
            this.paymentIntent = paymentIntent;
        }
}
