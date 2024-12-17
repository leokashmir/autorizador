package com.mvp.autorizador.transacao.shared.exception;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class TransacaoException extends Exception {

    private String motivo;
}
