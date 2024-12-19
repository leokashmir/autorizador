package com.mvp.autorizador.transacao.presentation.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
