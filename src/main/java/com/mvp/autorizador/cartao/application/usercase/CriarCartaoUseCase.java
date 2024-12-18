package com.mvp.autorizador.cartao.application.usercase;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.repository.CartaoRepositoryImpl;
import com.mvp.autorizador.cartao.shared.exception.CartaoJaExistenteException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class CriarCartaoUseCase {

    private CartaoRepositoryImpl repository;

    public void adicionaSaldo(Cartao cartao) {
        cartao.setSaldoCartao(new BigDecimal("500.00"));
    }
    public void existeCartao(String numeroCartao) {
         repository.findByNumeroCartao(numeroCartao).ifPresent(cartao -> {
             throw  CartaoJaExistenteException.builder()
                     .numeroCartao(cartao.getNumeroCartao())
                     .senhaCartao(cartao.getSenhaCartao())
                     .build();
         });
    }
}
