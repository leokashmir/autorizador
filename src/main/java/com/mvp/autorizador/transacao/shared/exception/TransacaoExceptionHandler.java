package com.mvp.autorizador.transacao.shared.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.mvp.autorizador.transacao")
public class TransacaoExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<?> handleTransacaoException(TransacaoException exc,
                                                              HttpServletRequest request) {

        return new ResponseEntity<>(
                exc.getMotivo() ,
                null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<?> handleTransacaoConcorrenteException(OptimisticLockingFailureException exc,
                                                      HttpServletRequest request) {
        return new ResponseEntity<>(
                "O cartão foi modificado por outra transação. Tente novamente." ,
                null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
