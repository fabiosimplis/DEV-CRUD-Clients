package br.com.dev.cliente.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMenssage> errors = new ArrayList<>();


    public ValidationError(Instant timesamp, Integer status, String error, String path) {
        super(timesamp, status, error, path);
    }

    public List<FieldMenssage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMenssage(fieldName, message));
    }
}
