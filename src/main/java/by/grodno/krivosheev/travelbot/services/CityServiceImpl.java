package by.grodno.krivosheev.travelbot.services;

import by.grodno.krivosheev.travelbot.models.CityModel;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public List<CityModel> listCities() {
        List<CityModel> list = new ArrayList<>();
        // TODO: delete after connection with database
        list.add(new CityModel("Гродно", "Информация по городу Гродно"));
        list.add(new CityModel("Минск", "Информация по городу Минск"));

        return list;
    }

    @Override
    public void addCity(String name, String info) {
        // TODO: Added new city on database
    }

}
