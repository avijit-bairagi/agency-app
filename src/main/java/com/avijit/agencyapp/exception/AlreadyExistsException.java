package com.avijit.agencyapp.exception;

public class AlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    public AlreadyExistsException(String message) {
        super(message);
    }


    public AlreadyExistsException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode(){ return code; }
}
