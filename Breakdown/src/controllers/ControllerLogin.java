package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML
    private Button registerButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File branding = new File("images/mechanics.png");
        Image brandingImage = new Image(branding.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Error! Try Again");
        }
    }

    private void validateLogin() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String business_or_user;

        //Database connection
        //Goal: To get user_id or business_id
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query1 = "SELECT COUNT(*) FROM public.\"login\" WHERE username='" + username + "' AND password='" + password + "';";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);
            if (resultSet1.next() && resultSet1.getInt(1) == 1) {
                resultSet1.close();
                String query2 = "SELECT public.\"user\".user_id FROM public.\"user\", public.\"login\" WHERE public.\"user\".username = public.\"login\".username AND public.\"login\".username = '" + username + "';";
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(query2);
                if (resultSet2.next()) {
                    System.out.println(resultSet2.getInt(1));
                    Session.setId(resultSet2.getInt(1));
                    Session.setUser(true);
                    business_or_user = "userdashboard";
                } else {
                    String query3 = "SELECT public.\"business_owner\".business_owner_id FROM public.\"business_owner\", public.\"login\" WHERE public.\"business_owner\".username = public.\"login\".username AND public.\"login\".username = '" + username + "';";
                    Statement statement3 = connection.createStatement();
                    ResultSet resultSet3 = statement3.executeQuery(query3);
                    if (resultSet3.next()) {
                        System.out.println(resultSet3.getInt(1));
                        Session.setId(resultSet3.getInt(1));
                        Session.setUser(false);
                        business_or_user = "businessdashboard";
                    } else {
                        loginMessageLabel.setText("Database Error");
                        resultSet2.close();
                        resultSet3.close();
                        connection.close();
                        return;
                    }
                    resultSet3.close();
                }
                resultSet2.close();
                connection.close();

                Stage stage = (Stage) loginMessageLabel.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("ui_" + business_or_user + ".fxml"));
                stage.setScene(new Scene(root, 420, 380));
                stage.show();
            } else {
                resultSet1.close();
                connection.close();
                loginMessageLabel.setText("Invalid credentials");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_register.fxml"));
        stage.setScene(new Scene(root, 360, 600));
        stage.show();
    }
}
