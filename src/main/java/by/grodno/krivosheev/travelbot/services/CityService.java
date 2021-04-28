package by.grodno.krivosheev.travelbot.services;

import by.grodno.krivosheev.travelbot.entities.CityEntity;

import java.util.List;

public interface CityService {
    /**
     * Get list all cities
     * @return a {@code List} of elements {@code CityEntity}
     */
    List<CityEntity> listCities();

    /**
     * Add new city
     * @param name String
     * @param info String
     */
    void addCity(String name, String info);

    /**
     * Add new city
     * @param cityEntity {@code CityEntity}
     */
    void addCity(CityEntity cityEntity);

    /**
     * Edit city
     * @param id Integer
     * @param name String
     * @param info String
     */
    void editCity(int id, String name, String info);

    /**
     * Delete city
     * @param id Integer
     */
    void deleteCity(int id);

    /**
     * Get city
     * @param id Integer
     * @return {@code CityEntity}
     */
    CityEntity getCity(int id);

    /**
     * City information by city name
     * @param name String
     * @return String
     */
    String getInfo(String name);
}
