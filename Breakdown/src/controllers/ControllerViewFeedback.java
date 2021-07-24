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
import models.Feedback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerViewFeedback implements Initializable {
    @FXML
    public ListView<Feedback> feedbackListView;
    @FXML
    private Button feedbackBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        feedbackListView.setCellFactory(feedbackListView1 -> {
            try {
                return new FeedbackListCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        if (Session.isUser()) {
            try {
                String query = "SELECT * FROM public.\"feedback\" WHERE user_id = " + Session.getId() + ";";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(Integer.toString(resultSet.getInt(1)), resultSet.getString(6), resultSet.getString(2), Integer.toString(resultSet.getInt(5)), Integer.toString(resultSet.getInt(3)));
                    feedbackListView.getItems().add(feedback);
                }
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            try {
                String query = "SELECT FB.feedback_id, FB.title, FB.description, FB.mechanic_id, FB.rating FROM public.\"business_owner\" AS BO, public.\"business_owner_mechanic\" AS BOM, public.\"feedback\" AS FB WHERE BO.business_owner_id = BOM.business_owner_id AND FB.mechanic_id = BOM.mechanic_id AND BO.business_owner_id = " + Session.getId() + ";";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(Integer.toString(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3), Integer.toString(resultSet.getInt(4)), Integer.toString(resultSet.getInt(5)));
                    feedbackListView.getItems().add(feedback);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void setfeedbackBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) feedbackBackButton.getScene().getWindow();
        String board = (Session.isUser()) ? "userdashboard" : "businessdashboard";
        Parent root = FXMLLoader.load(getClass().getResource("ui_" + board + ".fxml"));
        stage.setScene(new Scene(root, 420, 370));
        stage.show();
    }
}

