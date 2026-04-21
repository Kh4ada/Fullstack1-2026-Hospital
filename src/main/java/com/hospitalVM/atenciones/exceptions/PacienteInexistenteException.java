package com.hospitalVM.atenciones.exceptions;

public class PacienteInexistenteException extends RuntimeException {
    public PacienteInexistenteException(String message) {
        super(message);
    }
}
