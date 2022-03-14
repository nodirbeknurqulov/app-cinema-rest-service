package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

// Nurkulov Nodirbek 3/14/2022  11:22 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Row  {

    @Id
    private int rowNumber;

    @OneToMany
    private List<Seat> seatList;
}
