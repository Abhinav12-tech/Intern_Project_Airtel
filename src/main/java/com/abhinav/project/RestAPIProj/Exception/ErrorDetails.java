package com.abhinav.project.RestAPIProj.Exception;

import java.util.Date;

//Error Definition Class to display customized format to user on occurrence of an error
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    //Constructor for the defined variables
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    //Getter for Timestamp
    public Date getTimestamp() {
        return timestamp;
    }

    //Setter for Timestamp
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    //Getter for Message
    public String getMessage() {
        return message;
    }

    //Setter for Message
    public void setMessage(String message) {
        this.message = message;
    }

    //Getter for Details
    public String getDetails() {
        return details;
    }

    //Setter for Details
    public void setDetails(String details) {
        this.details = details;
    }
}
