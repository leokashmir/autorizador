package com.mvp.autorizador.transacao.application.usercase;

import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import com.mvp.autorizador.transacao.shared.enums.AutorizacaoNegadaEnum;
import com.mvp.autorizador.transacao.shared.exception.TransacaoException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoUserCaseTest {

    private final TransacaoUserCase transacaoUserCase = new TransacaoUserCase();

    @Test
    void validarSaldo_SaldoSuficiente() {
        TransacaoDto transacaoDto =  new TransacaoDto("1234123678956548","1234", BigDecimal.valueOf(50.00));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234123678956548", "1234", BigDecimal.valueOf(100.00), 1L);

        assertDoesNotThrow(() -> transacaoUserCase.validarSaldo(transacaoDto, cartaoDto));
    }

    @Test
    void validarSaldo_SaldoInsuficiente() {
        TransacaoDto transacaoDto = new TransacaoDto("1234123678956548","1234", BigDecimal.valueOf(150.00));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234123678956548", "1234", BigDecimal.valueOf(100.00), 1L);

        TransacaoException exception = assertThrows(TransacaoException.class,
                () -> transacaoUserCase.validarSaldo(transacaoDto, cartaoDto));

        assertEquals(AutorizacaoNegadaEnum.SALDO_INSUFICIENTE.getValue(), exception.getMotivo());
    }

    @Test
    void validarSenha_SenhaCorreta() {
        TransacaoDto transacaoDto = new TransacaoDto("1234123678956548","1234", BigDecimal.valueOf(50.00));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234123678956548", "1234", BigDecimal.valueOf(100.00), 1L);

        assertDoesNotThrow(() -> transacaoUserCase.validarSenha(transacaoDto, cartaoDto));
    }

    @Test
    void validarSenha_SenhaIncorreta() {
        TransacaoDto transacaoDto =  new TransacaoDto("1234123678956548","9987898", BigDecimal.valueOf(50.00));
        CartaoDto cartaoDto = new CartaoDto(1L, "123456789", "1234", BigDecimal.valueOf(100.00), 1L);

        TransacaoException exception = assertThrows(TransacaoException.class,
                () -> transacaoUserCase.validarSenha(transacaoDto, cartaoDto));

        assertEquals(AutorizacaoNegadaEnum.SENHA_INVALIDA.getValue(), exception.getMotivo());
    }
}
