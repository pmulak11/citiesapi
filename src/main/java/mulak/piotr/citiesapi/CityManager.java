package mulak.piotr.citiesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityManager {

    private CityRepository cityRepository;
    @Autowired
    public CityManager(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public void deleteById(long id) {
        cityRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase() {
/*
        save(new City(1L, "New York", "United States", 10000000, 5543));
        save(new City(2L, "Los Angeles", "United States", 13000000, 7000));

 */
    }

}
