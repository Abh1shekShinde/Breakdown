package controllers;

import controllers.CardMechanicUserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import models.Mechanic;

import java.io.IOException;

public class MechanicUserListCell extends ListCell<Mechanic>{
    private final Pane pane;
    private final CardMechanicUserController cardMechanicUserController;

    public MechanicUserListCell() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card_mechanic_user.fxml"));
        pane = fxmlLoader.load();
        cardMechanicUserController = fxmlLoader.getController();
        setGraphic(pane);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Mechanic mechanic, boolean b) {
        super.updateItem(mechanic, b);
        cardMechanicUserController.setMechanic(mechanic);
    }
}