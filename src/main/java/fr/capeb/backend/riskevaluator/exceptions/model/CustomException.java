package fr.capeb.backend.riskevaluator.exceptions.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomException extends RuntimeException
{
    public CustomException(String message) {
        super(message);
    }
}