package com.plancontrol.api.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("Nenhum registro encontrado.");
    }
}
