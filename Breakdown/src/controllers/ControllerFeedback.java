package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerFeedback implements Initializable {
    @FXML
    public TextField feedbackTitle;
    @FXML
    public TextArea feedbackDescription;
    @FXML
    public ComboBox<String> feedbackCombo;

    @FXML
    public Label mechanicNameLabel;
    @FXML
    public Label mechanicPhoneLabel;
    @FXML
    public Label mechanicIdLabel;
    @FXML
    public Label feedbackErrorLabel;
    @FXML
    public Button feedbackBackButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        feedbackCombo.getItems().setAll("1", "2", "3", "4", "5");
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query = "SELECT * FROM public.\"mechanic\" WHERE mechanic_id = " + Session.getMechanic_id() + ";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                mechanicIdLabel.setText(Integer.toString(resultSet.getInt(1)));
                mechanicNameLabel.setText(resultSet.getString(2));
                mechanicPhoneLabel.setText(resultSet.getString(3));
                resultSet.close();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setfeedbackSubmitButton(ActionEvent event) throws SQLException {
        if (!feedbackTitle.getText().isBlank() && !feedbackDescription.getText().isBlank() && feedbackCombo.getValue() != null) {
            String title = feedbackTitle.getText();
            String description = feedbackDescription.getText();
            String rating = feedbackCombo.getValue();

            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO public.\"feedback\"(description, rating, user_id, mechanic_id, title) VALUES('" + description + "', " + rating + ", " + Session.getId() + ", " + Session.getMechanic_id() + ", '" + title + "');";
            statement.executeUpdate(query);
            feedbackErrorLabel.setText("Feedback Added");

        } else {
            feedbackErrorLabel.setText("Fields Missing");
        }
        feedbackTitle.clear();
        feedbackDescription.clear();
        feedbackCombo.setValue("");
    }

    public void setfeedbackBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) feedbackBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_searchmechanic.fxml"));
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
}
