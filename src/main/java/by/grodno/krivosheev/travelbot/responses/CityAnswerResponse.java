package by.grodno.krivosheev.travelbot.responses;

import by.grodno.krivosheev.travelbot.entities.CityEntity;

import org.springframework.http.HttpStatus;

/**
 * This class it format json the response a server
 */
public class CityAnswerResponse extends AnswerResponse {
    private final CityEntity city;

    public CityAnswerResponse(HttpStatus code, String message, CityEntity object) {
        super(code, message);
        this.city = object;
    }

    public CityEntity getCity() {
        return city;
    }
}
