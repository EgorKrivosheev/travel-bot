package by.grodno.krivosheev.travelbot.services;

import by.grodno.krivosheev.travelbot.entities.CityEntity;
import by.grodno.krivosheev.travelbot.repositories.CityRepository;

import org.springframework.dao.DuplicateKeyException;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> listCities() {
        return cityRepository.findAll();
    }

    @Override
    public void addCity(String name, String info) {
        addCity(new CityEntity(name, info));
    }

    @Override
    public void addCity(CityEntity cityEntity) {
        if (cityRepository.existsByName(cityEntity.getName())) {
            throw new DuplicateKeyException("Город с таким названием(" + cityEntity.getName() + ") уже есть!");
        }
        cityRepository.save(cityEntity);
    }

    @Override
    public void editCity(int id, String name, String info) {
        existById(id);
        CityEntity cityEntity = cityRepository.getById(id);
        cityEntity.setNameAndInfo(name, info);
        cityRepository.save(cityEntity);
    }

    @Override
    public void deleteCity(int id) {
        existById(id);
        cityRepository.deleteById(id);
    }

    @Override
    public CityEntity getCity(int id) {
        existById(id);
        return cityRepository.getById(id);
    }

    @Override
    public String getInfo(String name) {
        if (!cityRepository.existsByName(name)) {
            return "Информация о городе с таким названием не найдена!";
        }
        return cityRepository.getByName(name).getInfo();
    }

    private void existById(int id) {
        if (!cityRepository.existsById(id)) {
            throw new EntityNotFoundException("Не найден город под №" + id);
        }
    }
}
