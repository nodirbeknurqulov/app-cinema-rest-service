package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;

// Nurkulov Nodirbek 3/14/2022  11:29 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name ="seats")
public class Seat extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    private Row row;

    @ManyToOne
    private PriceCategory priceCategory;

    public Seat(Integer number, PriceCategory priceCategory) {
        this.number = number;
        this.priceCategory = priceCategory;
    }

}
