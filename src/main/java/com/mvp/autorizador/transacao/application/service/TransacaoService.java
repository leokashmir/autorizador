package com.mvp.autorizador.transacao.application.service;

import com.mvp.autorizador.cartao.application.service.CartaoService;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.transacao.application.usercase.TransacaoUserCase;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import com.mvp.autorizador.transacao.shared.enums.AutorizacaoNegadaEnum;
import com.mvp.autorizador.transacao.shared.exception.TransacaoException;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class TransacaoService {

    private CartaoService cartaoService;
    private TransacaoUserCase transacaoUserCase;

    @Transactional
    public void autorizaTransacao(TransacaoDto transacaoDto)  {

        CartaoDto cartao = cartaoService.obterDadosCartao(
                        new CartaoDto(null, transacaoDto.numeroCartao(), null, null, null))
                .orElseThrow(() -> TransacaoException.builder()
                        .motivo(AutorizacaoNegadaEnum.CARTAO_INEXISTENTE.getValue())
                        .build());

        transacaoUserCase.validarSenha(transacaoDto, cartao);
        transacaoUserCase.ValidarSaldo(transacaoDto, cartao);

        try {
            cartaoService.AtualizaDadosCartao(autualizarValorCartao(transacaoDto, cartao));
        } catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException(null);
        }
    }

    private CartaoDto autualizarValorCartao(TransacaoDto transacaoDto, CartaoDto cartao) {
        var resultado = cartao.saldo().subtract(transacaoDto.valor());
        return new CartaoDto(cartao.id(), cartao.numeroCartao(), cartao.senha(), resultado, cartao.version());
    }


}
