package com.mvp.autorizador.transacao.presentation.dto;

import java.math.BigDecimal;

public record TransacaoDto(
         String numeroCartao, String senhaCartao, BigDecimal valor) {
}
