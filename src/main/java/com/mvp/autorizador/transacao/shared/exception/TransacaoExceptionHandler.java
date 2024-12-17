package com.mvp.autorizador.transacao.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransacaoExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<?> handleTransacaoException(TransacaoException exc,
                                                              HttpServletRequest request) {

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .motivo(exc.getMotivo())
                        .build() ,
                null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
