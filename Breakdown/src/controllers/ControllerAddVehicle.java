package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAddVehicle {
    @FXML
    private Button addVehicleButton;
    @FXML
    private Button backVehicleButton;
    @FXML
    private Label errorAddVehicleLabel;
    @FXML
    private TextField vehicleModelTxtField;
    @FXML
    private TextField vehicleTypeTxtField;
    @FXML
    private TextField vehicleNumberTxtField;
    @FXML
    private TextField engineTypeTxtField;

    public void setAddVehicleButton(ActionEvent event) {
        if (!vehicleTypeTxtField.getText().isBlank() && !vehicleNumberTxtField.getText().isBlank() && !engineTypeTxtField.getText().isBlank() && !vehicleModelTxtField.getText().isBlank()) {
            validateAddVehicle();
        } else {
            errorAddVehicleLabel.setText("Enter all fields");
        }
    }

    private void validateAddVehicle() {
        String vehicleType = vehicleTypeTxtField.getText();
        String vehicleNumber = vehicleNumberTxtField.getText();
        String engineType = engineTypeTxtField.getText();
        String modelName = vehicleModelTxtField.getText();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query1 = "INSERT INTO public.\"vehicle\"(type, vehicle_number,model_name,engine_type,user_id ) VALUES('" + vehicleType + "', " + vehicleNumber + ", '" + modelName + "', '" + engineType + "', " + Session.getId() + ") ;";
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate(query1);
            errorAddVehicleLabel.setText("Vehicle Added!");
            vehicleModelTxtField.clear();
            vehicleNumberTxtField.clear();
            vehicleTypeTxtField.clear();
            engineTypeTxtField.clear();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setBackVehicleButton(ActionEvent event) throws  IOException {
        Stage stage = (Stage)backVehicleButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_userdashboard.fxml"));
        stage.setScene(new Scene(root, 420, 380));
        stage.show();
    }
}
