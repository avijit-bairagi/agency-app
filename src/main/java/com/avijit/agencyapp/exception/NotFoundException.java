package com.avijit.agencyapp.exception;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    public NotFoundException(String message) {
        super(message);
    }


    public NotFoundException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode(){ return code; }
}
