package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Mechanic;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerSearchMechanic implements Initializable {
    @FXML
    public Button searchMechanicBackButton;
    @FXML
    public Label searchMechanicErrorLabel;
    @FXML
    public ListView<Mechanic> mechanicListView;
    @FXML
    private TextField searchMechanicTxtfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mechanicListView.setCellFactory(mechanicListView1 -> {
            try {
                return new MechanicUserListCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public void setSearchMechanicBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage)searchMechanicBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_userdashboard.fxml"));
        stage.setScene(new Scene(root, 420 , 380));
        stage.show();
    }

    public void setSearchMechanicButton(ActionEvent event) throws SQLException {
        if (searchMechanicTxtfield.getText().isBlank()) {
            searchMechanicErrorLabel.setText("Search field cannot be empty");
            return;
        }
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM public.\"mechanic\" WHERE location LIKE '%" + searchMechanicTxtfield.getText() + "%';";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Mechanic mechanic = new Mechanic(resultSet.getInt(1), resultSet.getString(2),(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5));
            mechanicListView.getItems().add(mechanic);
        }
    }
}
