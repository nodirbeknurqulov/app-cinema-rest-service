package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/14/2022  10:36 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbsEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Cart cart;

    private Date date_of_birth;

    @ManyToMany
    private List<Role> roles;

    @ManyToMany
    private List<Permission> permissions;

    @OneToOne
    private PurchasedHistory purchasedHistory;
}
