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
import models.Mechanic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerViewMechanic implements Initializable {
    @FXML
    private Button viewMechanicBackButton;
    @FXML
    private ListView<Mechanic> mechanicListView;


    public void setviewMechanicBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewMechanicBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_businessdashboard.fxml"));
        stage.setScene(new Scene(root, 420, 370));
        stage.show();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        mechanicListView.setCellFactory(mechanicListView -> {
            try {
                return new MechanicBusinessListCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM public.\"mechanic\"";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Mechanic mechanic = new Mechanic(resultSet.getInt(1), resultSet.getString(2),(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5));
                mechanicListView.getItems().add(mechanic);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
