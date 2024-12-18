package com.mvp.autorizador.cartao.shared.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartaoJaExistenteException extends RuntimeException {

    private String numeroCartao;
    private String senhaCartao;
}
