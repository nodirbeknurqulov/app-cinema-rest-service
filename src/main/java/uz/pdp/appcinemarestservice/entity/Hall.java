package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/14/2022  11:13 PM

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double vipAdditionalFeeInPercentage;

    @JsonIgnore
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rowList;

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, Double vipAdditionalFeeInPercent) {
        this.name = name;
        this.vipAdditionalFeeInPercentage = vipAdditionalFeeInPercent;
    }

}
