package mulak.piotr.citiesapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM CITY city WHERE city.id = :id")
    City getById(@Param("id") int id);
}
