package uz.pdp.appcinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcinemarestservice.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
    boolean existsByFullNameAndId(String fullName, Integer id);
}
