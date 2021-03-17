package by.grodno.krivosheev.travelbot.entities;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ListCityAnswerEntity extends AnswerEntity {
    private final List<CityEntity> cities;

    public ListCityAnswerEntity(HttpStatus code, String message, List<CityEntity> cities) {
        super(code, message);
        this.cities = cities;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

}
