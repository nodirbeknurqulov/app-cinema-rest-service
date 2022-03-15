package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

// Nurkulov Nodirbek 3/15/2022  6:42 AM

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "price_categories")
public class PriceCategory extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double additionalFeeInPercentage;

    @Column(nullable = false)
    private String color;

    @OneToMany
    private List<Seat> seat;
}
