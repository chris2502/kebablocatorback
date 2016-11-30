package org.kebablocator.exception;

/**
 * Created by ebongue on 11/11/16.
 */
public class NoKebabFoundException extends RuntimeException{
    public NoKebabFoundException(String message){
        super(message);
    }
}
