package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

// Nurkulov Nodirbek 3/14/2022  11:22 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "rows")
public class Row extends AbsEntity{

    @Column(nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne
    private Hall hall;

    public Row(Integer number, Hall hall) {
        this.number = number;
        this.hall = hall;
    }

}
