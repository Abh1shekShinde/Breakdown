package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Feedback;

public class CardFeedbackController {
    private Feedback feedback;
    @FXML
    public Label feedbackMechanicId;
    public Label feedbackTitle;
    public Label feedbackDescription;
    public Label feedbackRating;

    public void setFeedback(Feedback feedback){
        if (feedback == null) {
            return;
        }
        this.feedback=feedback;

        feedbackMechanicId.setText("Mechanic Id:  " + feedback.getMechanicId());
        feedbackTitle.setText("Title:  " + feedback.getTitle());
        feedbackDescription.setText("Description:  " + feedback.getDescription());
        feedbackRating.setText("Rating: " + feedback.getRating());
    }
}
