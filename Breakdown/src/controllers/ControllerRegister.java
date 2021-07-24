package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable {
    @FXML
    private ImageView registerImage;
    @FXML
    private TextField registerName;
    @FXML
    private TextField registerEmail;
    @FXML
    private TextField registerCard;
    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private PasswordField registerConfirmPassword;
    @FXML
    private Label registerMessageLabel;
    @FXML
    private ComboBox<String> registerCombobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file= new File("images/whiteuser.png");
        Image brandingImage = new Image(file.toURI().toString());
        registerImage.setImage(brandingImage);
        registerCombobox.getItems().setAll("Business", "User");
    }

    public void registerButtonOnAction(ActionEvent event) {
        if (!registerName.getText().isBlank() &&
                !registerUsername.getText().isBlank() &&
                !registerCard.getText().isBlank() &&
                !registerPassword.getText().isBlank()) {
            if (registerPassword.getText().equals(registerConfirmPassword.getText())) {
                validateForm();
            } else {
                registerMessageLabel.setText("Password did not match!");
            }

        } else {
            registerMessageLabel.setText("Some fields missing!");
        }
    }

    private void validateForm() {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String card = registerCard.getText();
        String password = registerPassword.getText();
        String email = registerEmail.getText();
        String comboBoxVal = registerCombobox.getValue();
        if (comboBoxVal == null) {
            registerMessageLabel.setText("Select Business or User");
            return;
        }
        String tableName = comboBoxVal.equals("Business") ? "business_owner": (comboBoxVal.equals("User") ? "user" : "invalid");

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            String query1 = "SELECT COUNT(*) FROM public.\"login\" WHERE username='" + username +"';" ;
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);
            if (resultSet1.next() && resultSet1.getInt(1) == 1) {
                registerMessageLabel.setText("Username exists!");
                resultSet1.close();
                connection.close();
                return;
            }
            resultSet1.close();
            String query2 = "SELECT COUNT(*) FROM public.\"register\" WHERE aadharcard_no=" + card +";" ;
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);
            while (resultSet2.next()){
                if (resultSet2.getInt(1) == 1) {
                    registerMessageLabel.setText("Aadhar card number already registered!");
                    resultSet2.close();
                    connection.close();
                    return;
                }
            }
            resultSet2.close();

            String query3 = "INSERT INTO public.\"register\"(name, aadharcard_no, email_id) " +
                    "VALUES('" + name + "', " + card + ", '" + email + "');";
            Statement statement3 = connection.createStatement();
            statement3.executeUpdate(query3);


            String query4 = "INSERT INTO public.\"login\"(username, password) VALUES('" + username + "', '" + password + "');";
            Statement statement4 = connection.createStatement();
            statement4.executeUpdate(query4);

            String query5 = "SELECT register_id FROM public.\"register\" WHERE aadharcard_no = " + card + ";";
            Statement statement5 = connection.createStatement();
            ResultSet resultSet5 = statement5.executeQuery(query5);
            int reg_id = 0;
            if (resultSet5.next()) {
                reg_id = resultSet5.getInt(1);
            }

            String query6 = "INSERT INTO public.\"" + tableName +"\"(registration_id, username) VALUES(" + reg_id +", '" + username + "');";
            Statement statement6 = connection.createStatement();
            statement6.executeUpdate(query6);

            registerMessageLabel.setText("Registration Successful!");

            registerName.clear();
            registerEmail.clear();
            registerCard.clear();
            registerUsername.clear();
            registerPassword.clear();
            registerConfirmPassword.clear();
            registerCombobox.setValue("Register as");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerMessageLabel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_login.fxml"));
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }
}
