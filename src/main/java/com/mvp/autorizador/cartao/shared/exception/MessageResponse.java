package com.mvp.autorizador.cartao.shared.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Builder @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

    private String motivo;
    private String numeroCartao;
    private String senhaCartao;
}
