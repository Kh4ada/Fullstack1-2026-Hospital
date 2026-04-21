package com.hospitalVM.atenciones.exceptions;

public class PacienteExistenteException extends RuntimeException {
    public PacienteExistenteException(String message) {
        super(message);
    }
}
