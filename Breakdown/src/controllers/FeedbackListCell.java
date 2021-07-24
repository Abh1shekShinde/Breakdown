package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import models.Feedback;

import java.io.IOException;

public class FeedbackListCell extends ListCell<Feedback> {
    private final Pane pane;
    private final CardFeedbackController cardFeedbackController;

    public FeedbackListCell() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card_feedback.fxml"));
        pane = fxmlLoader.load();
        cardFeedbackController = fxmlLoader.getController();
        setGraphic(pane);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Feedback feedback, boolean b) {
        super.updateItem(feedback, b);
        cardFeedbackController.setFeedback(feedback);
    }
}

