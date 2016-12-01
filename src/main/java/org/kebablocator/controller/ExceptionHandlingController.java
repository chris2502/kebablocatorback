package org.kebablocator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Valentin on 01/12/2016.
 */

public abstract class ExceptionHandlingController {

    private static int NbErreurs = 0;

    @ExceptionHandler(Exception.class)
    public void addError() throws Exception {
        NbErreurs++;
        throw new Exception();
    }

    protected int getNbErreurs() {
        return NbErreurs;
    }
}
