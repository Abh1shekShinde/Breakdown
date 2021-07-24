package controllers;

import controllers.CardMechanicBusinessController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import models.Mechanic;

import java.io.IOException;

public class MechanicBusinessListCell extends ListCell<Mechanic>{
    private final Pane pane;
    private final CardMechanicBusinessController cardMechanicBusinessController;

    public MechanicBusinessListCell() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card_mechanic_business.fxml"));
        pane = fxmlLoader.load();
        cardMechanicBusinessController = fxmlLoader.getController();
        setGraphic(pane);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Mechanic mechanic, boolean b) {
        super.updateItem(mechanic, b);
        cardMechanicBusinessController.setMechanic(mechanic);
    }
}
