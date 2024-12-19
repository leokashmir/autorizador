package com.mvp.autorizador.transacao.shared.exception;


import com.mvp.autorizador.cartao.shared.exception.ErrorResponse;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.mvp.autorizador.transacao")
public class TransacaoExceptionHandler  {

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<?> handleTransacaoException(TransacaoException exc,
                                                      HttpServletRequest request) {

        return new ResponseEntity<>(exc.getMotivo(),null,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<?> handleTransacaoConcorrenteException(OptimisticLockingFailureException exc,
                                                                 HttpServletRequest request) {
        return new ResponseEntity<>("O cartão foi modificado por outra transação. Tente novamente." ,null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException exc, WebRequest request) {
        List<ErrorResponse> errors = exc.getBindingResult().getAllErrors().stream()
                .map(error ->
                        new ErrorResponse(((FieldError) error).getField(),
                                error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}