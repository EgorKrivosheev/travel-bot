package by.grodno.krivosheev.travelbot.models;

/**
 * Abstract model for create new models
 */
public abstract class AbstractModel {
    private final String name;
    private final String info;

    public AbstractModel(String name, String  info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

}
