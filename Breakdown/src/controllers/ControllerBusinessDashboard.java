package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerBusinessDashboard {
    @FXML
    public Button feedbackBackButton;
    @FXML
    Button controlMechanicButton;
    @FXML
    Button viewOwnerFeedbackButton;
    @FXML
    Button viewMechanicButton;
    @FXML
    Button ownerLogoutButton;

    public void setControlMechanicButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) controlMechanicButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_add_delete_mechanic.fxml"));
        stage.setScene(new Scene(root, 454, 555));
        stage.show();
    }

    public void setviewOwnerFeedbackButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewOwnerFeedbackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_viewfeedback.fxml"));
        stage.setScene(new Scene(root, 444, 450));
        stage.show();
    }

    public void setviewMechanicButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewMechanicButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_viewmechanic.fxml"));
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }

    public void setownerLogoutButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ownerLogoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_login.fxml"));
        stage.setScene(new Scene(root, 550, 400));
        stage.show();
    }


}
