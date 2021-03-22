package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Drink;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkRepository extends CrudRepository<Drink,Integer> {

    @Query("SELECT v FROM Drink v WHERE " +
            "(:maxPrijs IS NULL OR v.price <= :maxPrijs) AND " +
            "(:alcoholic IS NULL OR v.alcoholic=:alcoholic) AND " +
            "(:light IS NULL OR v.light=:light) ")
    List<Drink> findByFilter(@Param("maxPrijs") Double maxPrijs,
                             @Param("alcoholic") Boolean alcoholic,
                             @Param("light") Boolean light);
}
