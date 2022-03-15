package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/15/2022  8:33 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Entity(name = "carts")
public class Cart extends AbsEntity {

}
