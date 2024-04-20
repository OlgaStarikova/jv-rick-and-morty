package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonageRepository extends JpaRepository<Personage, Long> {
    List<Personage> findAllByNameContainsIgnoreCase(String name);
}
