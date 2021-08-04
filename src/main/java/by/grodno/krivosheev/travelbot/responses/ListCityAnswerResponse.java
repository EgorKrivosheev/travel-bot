package by.grodno.krivosheev.travelbot.responses;

import by.grodno.krivosheev.travelbot.entities.CityEntity;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This class it format json the response a server
 */
public class ListCityAnswerResponse extends AnswerResponse {
    private final List<CityEntity> cities;

    public ListCityAnswerResponse(HttpStatus code, String message, List<CityEntity> cities) {
        super(code, message);
        this.cities = cities;
    }

    public List<CityEntity> getCities() {
        return cities;
    }
}
