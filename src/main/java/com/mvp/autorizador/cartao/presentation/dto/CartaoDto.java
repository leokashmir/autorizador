package com.mvp.autorizador.cartao.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CartaoDto (Long id, String numeroCartao, String senha, BigDecimal saldo, Integer version){}