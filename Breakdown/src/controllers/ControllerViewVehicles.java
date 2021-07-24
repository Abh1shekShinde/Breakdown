package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Vehicle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerViewVehicles implements Initializable {
    @FXML
    ListView<Vehicle> vehicleListView;
    @FXML
    Button viewVehicleBackButton;

    public void setViewVehicleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewVehicleBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_userdashboard.fxml"));
        stage.setScene(new Scene(root, 420, 380));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleListView.setCellFactory(vehicleListView1 -> {
            try {
                return new VehicleListCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM public.\"vehicle\" WHERE vehicle.user_id = " + Session.getId();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle(resultSet.getString(1), Integer.toString(resultSet.getInt(2)), resultSet.getString(3), resultSet.getString(4));
                vehicleListView.getItems().add(vehicle);
//                System.out.println(vehicle.toString());
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
