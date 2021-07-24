package models;

public class Vehicle {
    private String type;
    private String number;
    private String model;
    private String engineType;

    public Vehicle(String type, String number, String model, String engineType) {
        this.type = type;
        this.number = number;
        this.model = model;
        this.engineType = engineType;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getEngineType() {
        return engineType;
    }

    @Override
    public String toString() {
        return "Vehicle {" + getEngineType() + ", " + getType() + ", " + getModel() + "}";
    }
}
