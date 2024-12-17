package com.mvp.autorizador.cartao.shared.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class CartaoJaExistenteException extends RuntimeException {

    private String numeroCartao;
    private String senhaCartao;
}
