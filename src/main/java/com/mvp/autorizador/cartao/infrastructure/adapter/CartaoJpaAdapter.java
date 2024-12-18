package com.mvp.autorizador.cartao.infrastructure.adapter;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoJpaAdapter extends JpaRepository<Cartao, Long> {


    Optional<Cartao> findByNumeroCartao(String numeroCartao);

}
