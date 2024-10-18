package com.mareosenvios.utils;

public class ExParser {

    // devuelve la exception raiz que origina todos los males
    public static Throwable getRootException(Throwable exception) {
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}
