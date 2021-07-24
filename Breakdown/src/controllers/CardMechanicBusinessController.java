package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Mechanic;

public class CardMechanicBusinessController {

    private Mechanic mechanic;
    @FXML
    private Label mechanicNameLabel;
    @FXML
    private Label mechanicMobileLabel ;
    @FXML
    private Label mechanicServiceLabel;
    @FXML
    private Label mechanicLocationLabel;

    public void setMechanic(Mechanic mechanic){
        if (mechanic == null) {
            return;
        }
        this.mechanic=mechanic;
        mechanicNameLabel.setText("Mechanic's Name: " + mechanic.getMechanicName());
        mechanicMobileLabel.setText("Mechanic's Phone Number:  " + mechanic.getMechanicMobile());
        mechanicServiceLabel.setText("Services Provided:  " + mechanic.getMechanicService());
        mechanicLocationLabel.setText("Location:  " + mechanic.getMechanicLocation());
    }

}
