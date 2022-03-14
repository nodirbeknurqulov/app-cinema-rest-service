package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/14/2022  11:13 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Hall extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double vipAdditionalFeeInPercentage;

    @OneToMany
    private List<Row> rowList;
}
