package com.mvp.autorizador.transacao.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransacaoDto(

        @NotNull(message = "Numero do Cartao é obrigatório ")
        @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 caracteres.")
        String numeroCartao,

        @NotNull(message = "Senha é obrigatório ")
        String senhaCartao,

        @NotNull(message = "Saldo é obrigatório ")
        @PositiveOrZero
        BigDecimal valor) {
}
