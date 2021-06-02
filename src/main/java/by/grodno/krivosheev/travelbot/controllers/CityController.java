package by.grodno.krivosheev.travelbot.controllers;

import by.grodno.krivosheev.travelbot.entities.AnswerEntity;
import by.grodno.krivosheev.travelbot.entities.CityAnswerEntity;
import by.grodno.krivosheev.travelbot.entities.CityEntity;
import by.grodno.krivosheev.travelbot.entities.ListCityAnswerEntity;
import by.grodno.krivosheev.travelbot.services.CityService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/API", produces = "application/json")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/city/add")
    public ResponseEntity<AnswerEntity> addCity(@RequestParam("name") String name, @RequestParam("info") String info) {
        if (name.equals("") || info.equals("")) {
            return badRequest("Поля не могут быть пустыми!");
        }
        try {
            cityService.addCity(name, info);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>(new AnswerEntity(HttpStatus.CONFLICT, e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return internalServerError();
        }
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.OK, "Новый город:" + name), HttpStatus.OK);
    }

    // Request param id is a string because have NumberFormatException 🤢
    @PutMapping(value = "/city/edit")
    public ResponseEntity<AnswerEntity> editCity(@RequestParam("id") String id, @RequestParam("name") String name,
                                           @RequestParam("info") String info) {
        if (name.equals("") || info.equals("")) {
            return badRequest("Поля не могут быть пустыми!");
        }
        try {
            int buf = Integer.parseInt(id);
            cityService.editCity(buf, name, info);
        } catch (NumberFormatException e) {
            return badRequest("№ должен быть числом!");
        } catch (EntityNotFoundException e) {
            return notFound(e);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new AnswerEntity(HttpStatus.CONFLICT, "Превышена длина одного из параметров!"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return internalServerError();
        }
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.OK, "Новая информация для №" + id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/city/delete")
    public ResponseEntity<AnswerEntity> deleteCity(@RequestParam("id") String id) {
        if (id.equals("")) {
            return badRequest("В запросе на удаление № не может быть пустым!");
        }
        try {
            int buf = Integer.parseInt(id);
            cityService.deleteCity(buf);
        } catch (NumberFormatException e) {
            return badRequest("В запросе на удаление № должен быть числом!");
        } catch (EntityNotFoundException e) {
            return notFound(e);
        } catch (Exception e) {
            return internalServerError();
        }
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.OK, "Удалён город под №" + id), HttpStatus.OK);
    }

    @GetMapping(value = "/city")
    public ResponseEntity<AnswerEntity> getCity(@RequestParam("id") String id) {
        CityEntity city;
        try {
            int buf = Integer.parseInt(id);
            city = cityService.getCity(buf);
        } catch (NumberFormatException e) {
            return badRequest("В запросе № должен быть числом!");
        } catch (EntityNotFoundException e) {
            return notFound(e);
        } catch (Exception e) {
            return internalServerError();
        }
        return new ResponseEntity<>(new CityAnswerEntity(HttpStatus.OK, "Получен город под №" + id, city), HttpStatus.OK);
    }

    @GetMapping(value = "/cities")
    public ResponseEntity<AnswerEntity> cities() {
        List<CityEntity> listCities;
        try {
            listCities = cityService.listCities();
        } catch (Exception e) {
            return internalServerError();
        }
        return new ResponseEntity<>(new ListCityAnswerEntity(HttpStatus.OK, "Список всех городов!", listCities), HttpStatus.OK);
    }

    private ResponseEntity<AnswerEntity> badRequest(String msg) {
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.BAD_REQUEST, msg), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<AnswerEntity> notFound(EntityNotFoundException e) {
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<AnswerEntity> internalServerError() {
        return new ResponseEntity<>(new AnswerEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Что-то сломалось!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
