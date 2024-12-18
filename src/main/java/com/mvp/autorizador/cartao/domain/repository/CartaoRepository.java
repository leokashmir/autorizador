package com.mvp.autorizador.cartao.domain.repository;

import com.mvp.autorizador.cartao.domain.model.Cartao;

import java.util.Optional;


public interface CartaoRepository  {

    Optional<Cartao> findByNumeroCartao(String numeroCartao);
    Cartao save(Cartao cartao);

}