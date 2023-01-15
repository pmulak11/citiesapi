package mulak.piotr.citiesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityApi {

    private CityManager cities;
    @Autowired
    public CityApi(CityManager cities) {
        this.cities = cities;
    }

    @GetMapping("/all")
    public Iterable<City> getAll() {
        return cities.findAll();
    }
    @GetMapping
    public Optional<City> getById(@RequestParam long index) {
        return cities.findById(index);
    }
    @PostMapping
    public City addCity(@RequestBody City city) {
        return cities.save(city);
    }
    @DeleteMapping
    public void deleteCity(@RequestParam long index) {
        cities.deleteById(index);
    }
    @PutMapping
    public City updateCity(@RequestBody City city) {
        return cities.save(city);
    }

}
