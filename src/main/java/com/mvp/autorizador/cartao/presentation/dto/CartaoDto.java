package com.mvp.autorizador.cartao.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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