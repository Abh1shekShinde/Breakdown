package models;

public class Mechanic {
    private int mehanicId;
    private String mechanicName;
    private String mechanicMobile;
    private String mechanicService;
    private String mechanicLocation;

    public Mechanic(int mechanicId, String mechanicName, String mechanicMobile, String mechanicService, String mechanicLocation) {
        this.mehanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.mechanicMobile = mechanicMobile;
        this.mechanicService = mechanicService;
        this.mechanicLocation = mechanicLocation;
    }

    public int getMehanicId() {
        return mehanicId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public String getMechanicMobile() {
        return mechanicMobile;
    }

    public String getMechanicService() {
        return mechanicService;
    }

    public String getMechanicLocation() {
        return mechanicLocation;
    }

    @Override
    public String toString() {
        return "Mechanic {" + getMechanicName() + ", " + getMechanicMobile() + ", " + getMechanicService() + ", '"+ getMechanicLocation()+ "'}";
    }
}
