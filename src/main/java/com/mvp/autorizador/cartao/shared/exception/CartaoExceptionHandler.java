package com.mvp.autorizador.cartao.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "com.mvp.autorizador.cartao")
public class CartaoExceptionHandler  {

    @ExceptionHandler(CartaoJaExistenteException.class)
    public ResponseEntity<MessageResponse> handleCartaoJaExistenteException(CartaoJaExistenteException exc,
                                                   HttpServletRequest request) {

        return new ResponseEntity<>(
                MessageResponse.builder().numeroCartao(exc.getNumeroCartao())
                        .senhaCartao(exc.getSenhaCartao())
                        .build() ,
                null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CartaoNaoExistenteException.class)
    public ResponseEntity<Void> handleCartaoJaExistenteException(CartaoNaoExistenteException exc,
                                                              HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
