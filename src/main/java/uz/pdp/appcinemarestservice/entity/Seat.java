package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Nurkulov Nodirbek 3/14/2022  11:29 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name ="seats")
public class Seat {
    @Id
    private int seatNumber;
}
