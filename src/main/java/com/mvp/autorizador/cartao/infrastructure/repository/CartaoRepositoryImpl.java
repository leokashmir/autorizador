package com.mvp.autorizador.cartao.infrastructure.repository;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.domain.repository.CartaoRepository;
import com.mvp.autorizador.cartao.infrastructure.adapter.CartaoJpaAdpater;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component @AllArgsConstructor
public class CartaoRepositoryImpl implements CartaoRepository {


    private CartaoJpaAdpater cartaoJpaAdpater;

    @Override
    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return cartaoJpaAdpater.findByNumeroCartao(numeroCartao);
    }

    @Override
    public Cartao save(Cartao cartao) {
        return cartaoJpaAdpater.save(cartao);
    }


}
