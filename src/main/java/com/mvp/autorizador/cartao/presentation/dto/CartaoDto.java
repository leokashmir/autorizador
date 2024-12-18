package com.mvp.autorizador.cartao.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CartaoDto (

        Long id,

        @NotNull(message = "Numero do Cartao é obrigatório ")
        @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 caracteres.")
        String numeroCartao,

        @NotNull(message = "Senha é obrigatório ")
        String senha,


        @PositiveOrZero
        BigDecimal saldo,

        Long version){}