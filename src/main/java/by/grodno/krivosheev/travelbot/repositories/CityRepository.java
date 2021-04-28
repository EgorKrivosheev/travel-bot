package by.grodno.krivosheev.travelbot.repositories;

import by.grodno.krivosheev.travelbot.entities.CityEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    CityEntity getById(int id);

    CityEntity getByName(String name);

    boolean existsByName(String name);
}
