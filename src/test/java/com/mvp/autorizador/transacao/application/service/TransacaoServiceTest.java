package com.mvp.autorizador.transacao.application.service;

import com.mvp.autorizador.cartao.application.service.CartaoService;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.transacao.application.usercase.TransacaoUserCase;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import com.mvp.autorizador.transacao.shared.enums.AutorizacaoNegadaEnum;
import com.mvp.autorizador.transacao.shared.exception.TransacaoException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    private final CartaoService cartaoService = mock(CartaoService.class);
    private final TransacaoUserCase transacaoUserCase = mock(TransacaoUserCase.class);
    private final TransacaoService transacaoService = new TransacaoService(cartaoService, transacaoUserCase);

    @Test
    void autorizarTransacao_Sucesso() {
        TransacaoDto transacaoDto = new TransacaoDto("1234567890123456", "1234", BigDecimal.valueOf(100));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234567890123456", "1234", BigDecimal.valueOf(500), 1L);

        when(cartaoService.obterDadosCartao(any())).thenReturn(Optional.of(cartaoDto));

        transacaoService.autorizaTransacao(transacaoDto);

        verify(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);
        verify(transacaoUserCase).validarSaldo(transacaoDto, cartaoDto);
        verify(cartaoService).atualizaDadosCartao(any(CartaoDto.class));
    }

    @Test
    void  naoAutorizarTransacao_CartaoInexistente() {
        TransacaoDto transacaoDto = new TransacaoDto("1234567890123456", "1234", BigDecimal.valueOf(100));

        when(cartaoService.obterDadosCartao(any())).thenReturn(Optional.empty());

        TransacaoException exception = assertThrows(TransacaoException.class, () -> transacaoService.autorizaTransacao(transacaoDto));

        assertEquals(AutorizacaoNegadaEnum.CARTAO_INEXISTENTE.getValue(), exception.getMotivo());
        verify(cartaoService).obterDadosCartao(any());
        verifyNoInteractions(transacaoUserCase);
    }

    @Test
    void validarThrowException_OptimisticLockingFailure() {
        TransacaoDto transacaoDto = new TransacaoDto("1234567890123456", "1234", BigDecimal.valueOf(100));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234567890123456", "1234", BigDecimal.valueOf(500), 1L);

        when(cartaoService.obterDadosCartao(any())).thenReturn(Optional.of(cartaoDto));
        doThrow(new OptimisticLockingFailureException("Conflict")).when(cartaoService).atualizaDadosCartao(any());

        assertThrows(OptimisticLockingFailureException.class, () -> transacaoService.autorizaTransacao(transacaoDto));

        verify(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);
        verify(transacaoUserCase).validarSaldo(transacaoDto, cartaoDto);
        verify(cartaoService).atualizaDadosCartao(any(CartaoDto.class));
    }

    @Test
    void  naoAutorizarTransacao_SenhaInvalida() {
        TransacaoDto transacaoDto = new TransacaoDto("1234567890123456", "wrong-password", BigDecimal.valueOf(500));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234567890123456", "1234", BigDecimal.valueOf(500), 1L);

        when(cartaoService.obterDadosCartao(any())).thenReturn(Optional.of(cartaoDto));
        doThrow(TransacaoException.builder().motivo("Senha inválida").build())
                .when(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);

        TransacaoException exception = assertThrows(TransacaoException.class,
                () -> transacaoService.autorizaTransacao(transacaoDto));

        assertEquals("Senha inválida", exception.getMotivo());
        verify(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);
        verify(cartaoService, never()).atualizaDadosCartao(any(CartaoDto.class));
    }

    @Test
    void  naoAutorizarTransacao_SaldoInsuficiente() {
        TransacaoDto transacaoDto = new TransacaoDto("1234567890123456", "wrong-password", BigDecimal.valueOf(100));
        CartaoDto cartaoDto = new CartaoDto(1L, "1234567890123456", "1234", BigDecimal.valueOf(500), 1L);

        when(cartaoService.obterDadosCartao(any())).thenReturn(Optional.of(cartaoDto));
        doThrow(TransacaoException.builder().motivo("Senha inválida").build())
                .when(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);

        TransacaoException exception = assertThrows(TransacaoException.class,
                () -> transacaoService.autorizaTransacao(transacaoDto));

        assertEquals("Senha inválida", exception.getMotivo());
        verify(transacaoUserCase).validarSenha(transacaoDto, cartaoDto);
        verify(cartaoService, never()).atualizaDadosCartao(any(CartaoDto.class));
    }
}