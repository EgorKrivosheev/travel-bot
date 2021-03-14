package by.grodno.krivosheev.travelbot.services;

import by.grodno.krivosheev.travelbot.models.CityModel;

import java.util.List;

public interface CityService {

    /**
     * Get list all cities
     * @return a {@code List} of elements {@code CityModel}
     */
    List<CityModel> listCities();

    /**
     * Add new city and about his information
     * @param name String
     * @param info String
     */
    void addCity(String name, String info);

    // TODO: added editCity() and deleteCity()

}
