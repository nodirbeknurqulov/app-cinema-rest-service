package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 4/6/2022  11:55 AM

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
