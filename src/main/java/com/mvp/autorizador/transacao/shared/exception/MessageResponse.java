package com.mvp.autorizador.transacao.shared.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;


@Builder @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

    private String motivo;

}
