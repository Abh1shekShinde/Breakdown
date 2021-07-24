package models;

public class Feedback {
    String feedbackId;
    String title;
    String description;
    String mechanicId;
    String rating;

    public Feedback(String feedbackId, String title, String description, String mechanicId, String rating) {
        this.feedbackId = feedbackId;
        this.title = title;
        this.description = description;
        this.mechanicId = mechanicId;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }
}
