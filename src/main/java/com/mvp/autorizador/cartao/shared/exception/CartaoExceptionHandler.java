package com.mvp.autorizador.cartao.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.mvp.autorizador.cartao")
public class CartaoExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoJaExistenteException.class)
    public ResponseEntity<?> handleCartaoJaExistenteException(CartaoJaExistenteException exc,
                                                   HttpServletRequest request) {

        return new ResponseEntity<>(
                MessageResponse.builder().numeroCartao(exc.getNumeroCartao())
                        .senhaCartao(exc.getSenhaCartao())
                        .build() ,
                null,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CartaoNaoExistenteException.class)
    public ResponseEntity<?> handleCartaoJaExistenteException(CartaoNaoExistenteException exc,
                                                              HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
