package com.BJJ.BJJSite.Exceptions;

public class SessionAlreadyExistsException extends RuntimeException{
    public SessionAlreadyExistsException(String message){
        super(message);
    }
}
