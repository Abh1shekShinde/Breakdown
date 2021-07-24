package controllers;

import controllers.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Mechanic;

import java.io.IOException;

public class CardMechanicUserController {
    private Mechanic mechanic;
    public Label mechanicNameLabel;
    public Label mechanicMobileLabel;
    public Label mechanicServiceLabel;
    public Label mechanicLocationLabel;
    public Button mechanicAddFeedbackButton;
    public Button mechanicViewRatingsButton;

    public void setMechanic(Mechanic mechanic){
        if (mechanic == null) {
            return;
        }
        this.mechanic = mechanic;
        mechanicNameLabel.setText("Name: " + mechanic.getMechanicName());
        mechanicMobileLabel.setText("Phone Number:  " + mechanic.getMechanicMobile());
        mechanicServiceLabel.setText("Services:  " + mechanic.getMechanicService());
        mechanicLocationLabel.setText("Location:  " + mechanic.getMechanicLocation());
    }

    public void setAddFeedback(ActionEvent event) throws IOException {
        Session.setMechanic_id(mechanic.getMehanicId());
        Stage stage = (Stage) mechanicAddFeedbackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_feedback.fxml"));
        stage.setScene(new Scene(root, 404, 500));
        stage.show();
    }

    public void setViewRating(ActionEvent event) throws IOException {
        Session.setMechanic_id(mechanic.getMehanicId());
        Stage stage = (Stage) mechanicViewRatingsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_viewrating.fxml"));
        stage.setScene(new Scene(root, 730, 400));
        stage.show();
    }
}
