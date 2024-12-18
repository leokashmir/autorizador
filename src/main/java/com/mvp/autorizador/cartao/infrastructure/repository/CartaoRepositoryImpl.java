package com.mvp.autorizador.cartao.infrastructure.repository;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.domain.repository.CartaoRepository;
import com.mvp.autorizador.cartao.infrastructure.adapter.CartaoJpaAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @AllArgsConstructor
public class CartaoRepositoryImpl implements CartaoRepository {


    private CartaoJpaAdapter cartaoJpaAdpater;

    @Override
    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return cartaoJpaAdpater.findByNumeroCartao(numeroCartao);
    }

    @Override
    public Cartao save(Cartao cartao) {
        return cartaoJpaAdpater.save(cartao);
    }


}
