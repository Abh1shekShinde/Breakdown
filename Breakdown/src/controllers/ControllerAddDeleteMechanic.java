package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerAddDeleteMechanic {
    @FXML
    private Button mechanicAddButton;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteMechanicButton;
    @FXML
    private TextField mechanicNameTxtField;
    @FXML
    private TextField mechanicPhonenoTxtField;
    @FXML
    private TextField mechanicLocationTxtField;
    @FXML
    private TextArea servicesTxtArea;
    @FXML
    private Label errorAddMechanicLabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file= new File("images/mehanics.png");
        Image brandingImage = new Image(file.toURI().toString());

    }

    public void setbackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_businessdashboard.fxml"));
        stage.setScene(new Scene(root, 420, 370));
        stage.show();
    }

    public void setmechanicAddButton(ActionEvent event) throws SQLException {
        if (!mechanicNameTxtField.getText().isBlank() && !mechanicPhonenoTxtField.getText().isBlank() && !servicesTxtArea.getText().isBlank() && !mechanicLocationTxtField.getText().isBlank()) {
            validateAddMechanic();
        } else {
            errorAddMechanicLabel.setText("Enter all fields");
        }
    }

    private void validateAddMechanic() {
        String mechanicName = mechanicNameTxtField.getText();
        String mechanicPhoneNumber = mechanicPhonenoTxtField.getText();
        String mechanicServices = servicesTxtArea.getText();
        String mechanicLocation = mechanicLocationTxtField.getText();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query = "INSERT INTO public.\"mechanic\"(name, mobile_no, services, location) VALUES('" + mechanicName + "', " + mechanicPhoneNumber + ", '" + mechanicServices + "', '" + mechanicLocation + "') ;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            Connection connection1 = databaseConnection.getConnection();
            String query1 = "SELECT last_value FROM public.\"my_serial_mechanic\";";
            Statement statement1 = connection1.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);
            if (resultSet1.next()) {
                int mechanic_id = resultSet1.getInt(1);
                int business_owner_id = Session.getId();
                Connection connection2 = databaseConnection.getConnection();
                Statement statement2 = connection2.createStatement();
                String query2 = "INSERT INTO public.\"business_owner_mechanic\"(mechanic_id, business_owner_id) VALUES(" + mechanic_id + ", " + business_owner_id + ");";
                statement2.executeUpdate(query2);
            }
            resultSet1.close();
            connection1.close();
            errorAddMechanicLabel.setText("Mechanic Added!");
            mechanicNameTxtField.clear();
            mechanicPhonenoTxtField.clear();
            servicesTxtArea.clear();
            mechanicLocationTxtField.clear();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setdeleteMechanicButton(ActionEvent event) {
        if (!mechanicNameTxtField.getText().isBlank() && !mechanicPhonenoTxtField.getText().isBlank()) {
            validateDeleteMechanic();
        } else {
            errorAddMechanicLabel.setText("Enter Name and phone number");
        }
    }

    private void validateDeleteMechanic() {

        try {
            String mechanicName = mechanicNameTxtField.getText();
            String mechanicPhoneNumber = mechanicPhonenoTxtField.getText();

            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query2 = ("DELETE FROM  public.\"mechanic\" WHERE name= '" + mechanicName + "' AND mobile_no= '"+ mechanicPhoneNumber + "' ;");
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate(query2);
            errorAddMechanicLabel.setText("Mechanic Deleted!");
            mechanicNameTxtField.clear();
            mechanicPhonenoTxtField.clear();
            servicesTxtArea.clear();
            mechanicLocationTxtField.clear();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
