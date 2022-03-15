package uz.pdp.appcinemarestservice.repository;
// Nurkulov Nodirbek 3/15/2022  12:07 PM

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor,Integer> {
    boolean existsByFullName(String fullName);
}
