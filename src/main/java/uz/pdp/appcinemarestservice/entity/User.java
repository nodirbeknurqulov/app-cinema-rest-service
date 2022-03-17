package uz.pdp.appcinemarestservice.entity;
// Nurkulov Nodirbek 3/14/2022  10:36 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.enums.Gender;
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
    private String firstName,lastName,password,phoneNumber;

    @Column(nullable = false,unique = true)
    private String username;

    @OneToOne
    private Cart cart;

    private Date dateOfBirth;


    private Gender gender;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @ManyToMany
    @JoinTable(
            name = "users_permissions",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id", referencedColumnName = "id"))
    private List<Permission> permissions;


}
