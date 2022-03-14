package uz.pdp.appcinemarestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.Entity;

// Nurkulov Nodirbek 3/14/2022  10:58 PM
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "permissions")
public class Permission extends AbsEntity {
    private String permissionName;
}
