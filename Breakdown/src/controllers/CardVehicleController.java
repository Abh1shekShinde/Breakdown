package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Vehicle;

public class CardVehicleController {
    private Vehicle vehicle;
    @FXML
    private Label vehicleNumberLabel;
    @FXML
    private Label modelNameLabel;
    @FXML
    private Label engineTypeLabel;
    @FXML
    private Label vehicleTypeLabel;

    public void setVehicle(Vehicle vehicle){
        if (vehicle == null) {
//            System.out.println("Vehicle is null");
            return;
        }
        this.vehicle=vehicle;
        vehicleNumberLabel.setText("Vehicle Number:  " + vehicle.getNumber());
        modelNameLabel.setText("Vehicle Model:  " + vehicle.getModel());
        engineTypeLabel.setText("Vehicle Engine Type:  " + vehicle.getEngineType());
        vehicleTypeLabel.setText("Vehicle Type:  " + vehicle.getType());
    }

}
