package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
}
