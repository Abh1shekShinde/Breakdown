package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import models.Vehicle;

import java.io.IOException;

public class VehicleListCell extends ListCell<Vehicle> {
    private final Pane pane;
    private final CardVehicleController cardVehicleController;

    public VehicleListCell() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card_vehicle.fxml"));
        pane = fxmlLoader.load();
        cardVehicleController = fxmlLoader.getController();
        setGraphic(pane);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Vehicle vehicle, boolean b) {
        super.updateItem(vehicle, b);
        cardVehicleController.setVehicle(vehicle);
    }
}
