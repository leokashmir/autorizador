package com.mvp.autorizador.transacao.shared.enums;

import lombok.Getter;

@Getter
public enum AutorizacaoNegadaEnum {

    SALDO_INSUFICIENTE ("SALDO_INSUFICIENTE"),
    SENHA_INVALIDA ("SENHA_INVALIDA"),
    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

    private String value;

    AutorizacaoNegadaEnum(String mensagem) {
        this.value = mensagem;
    }
}
