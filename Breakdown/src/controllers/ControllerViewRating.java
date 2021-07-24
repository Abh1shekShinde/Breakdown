package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerViewRating implements Initializable {
    @FXML
    public Label mechanicRating;
    @FXML
    public Label mechanicId;
    @FXML
    public Label mechanicName;
    @FXML
    public Label mechanicPhone;
    @FXML
    public Label mechanicLocation;
    @FXML
    public PieChart mechanicChart;
    @FXML
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double avg = 0.0;
        mechanicRating.setText("Rating: N/A");
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT AVG(FB.rating) FROM public.\"feedback\" AS FB WHERE FB.mechanic_id = " + Session.getMechanic_id() + " GROUP BY FB.mechanic_id;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                avg = resultSet.getDouble(1);
                mechanicRating.setText(String.format("Rating: %.1f", avg));
                avg = avg * 20;
            }
            resultSet.close();

            Statement statement1 = connection.createStatement();
            String query1 = "SELECT * FROM public.\"mechanic\" WHERE mechanic_id = " + Session.getMechanic_id() + ";";
            ResultSet resultSet1 = statement1.executeQuery(query1);
            if (resultSet1.next()) {
                mechanicId.setText("Id: " + Integer.toString(resultSet1.getInt(1)));
                mechanicName.setText("Name: " + resultSet1.getString(2));
                mechanicPhone.setText("Phone: " + resultSet1.getString(3));
                mechanicLocation.setText("Location: " + resultSet1.getString(5));
            }
            resultSet1.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        mechanicChart.setStartAngle(90);
        mechanicChart.setLabelsVisible(false);
        mechanicChart.setLegendVisible(false);

        PieChart.Data blue = new PieChart.Data("", (int) avg);
        PieChart.Data grey = new PieChart.Data("", 100 - (int) avg);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(blue, grey);
        mechanicChart.setData(pieChartData);

        blue.getNode().setStyle("-fx-pie-color: #4071db;");
        grey.getNode().setStyle("-fx-pie-color: #8D818C;");
    }

    public void setBackButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_searchmechanic.fxml"));
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
}
