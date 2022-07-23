package com.abhinav.project.RestAPIProj.Exception;

//Class to handle APIException by returning error message to user
public class APIException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public APIException(String message){
        super(message);
    }
}
