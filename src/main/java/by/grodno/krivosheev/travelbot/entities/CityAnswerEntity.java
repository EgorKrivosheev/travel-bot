package by.grodno.krivosheev.travelbot.entities;

import org.springframework.http.HttpStatus;

public class CityAnswerEntity extends AnswerEntity {
    private final CityEntity city;

    public CityAnswerEntity(HttpStatus code, String message, CityEntity object) {
        super(code, message);
        this.city = object;
    }

    public CityEntity getCity() {
        return city;
    }

}
