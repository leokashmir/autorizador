package com.mvp.autorizador.transacao.application.usercase;

import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import com.mvp.autorizador.transacao.shared.enums.AutorizacaoNegadaEnum;
import com.mvp.autorizador.transacao.shared.exception.TransacaoException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransacaoUserCase {


    public void validarSaldo(TransacaoDto transacaoDto, CartaoDto cartao) {
        Optional.of(cartao)
                .filter(c -> c.saldo().compareTo(transacaoDto.valor()) >= 0)
                .orElseThrow(() -> TransacaoException.builder()
                        .motivo(AutorizacaoNegadaEnum.SALDO_INSUFICIENTE.getValue())
                        .build());
    }

    public void validarSenha(TransacaoDto transacaoDto, CartaoDto cartao) {
        Optional.of(cartao)
                .filter(c -> c.senha().equals(transacaoDto.senhaCartao()))
                .orElseThrow(() -> TransacaoException.builder()
                        .motivo(AutorizacaoNegadaEnum.SENHA_INVALIDA.getValue())
                        .build());
    }

}
