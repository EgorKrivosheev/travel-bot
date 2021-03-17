package by.grodno.krivosheev.travelbot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "CITY")
public class CityEntity {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column(name = "city_name", length = 25, nullable = false, unique = true)
    private String name = "";

    @Column(name = "city_info", length = 225, nullable = false)
    private String info = "";

    public CityEntity() {

    }

    /**
     * Constructor has 2 param: a {@code name}, {@code info} and auto-generation {@code id}
     * @param name String
     * @param info String
     */
    public CityEntity(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setNameAndInfo(String name, String info) {
        this.name = name;
        this.info = info;
    }

}
