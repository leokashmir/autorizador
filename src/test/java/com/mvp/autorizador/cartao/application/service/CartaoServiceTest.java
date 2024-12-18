package com.mvp.autorizador.cartao.application.service;


import com.mvp.autorizador.cartao.application.usercase.CriarCartaoUseCase;
import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.repository.CartaoRepositoryImpl;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.cartao.presentation.dto.SaldoDto;
import com.mvp.autorizador.cartao.shared.exception.CartaoNaoExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.OptimisticLockingFailureException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartaoServiceTest {

    @InjectMocks
    private CartaoService cartaoService;

    @Mock
    private CriarCartaoUseCase criarCartaoUseCase;

    @Mock
    private CartaoRepositoryImpl cartaoRepository;

    private Cartao cartao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartao = Cartao.builder()
                .id(1L)
                .numeroCartao("123456789")
                .senhaCartao("senha123")
                .saldoCartao(BigDecimal.valueOf(100.00))
                .version(1L)
                .build();
    }

    @Test
    void criarCartao_Success() {
        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        CartaoDto cartaoDto = cartaoService.criarCartao(cartao);

        verify(criarCartaoUseCase).existeCartao(cartao.getNumeroCartao());
        verify(criarCartaoUseCase).adicionaSaldo(cartao);
        verify(cartaoRepository).save(cartao);

        assertNotNull(cartaoDto);
        assertEquals(cartao.getNumeroCartao(), cartaoDto.numeroCartao());
        assertEquals(cartao.getSenhaCartao(), cartaoDto.senha());
    }

    @Test
    void obterSaldo_CartaoExistente() {
        when(cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        SaldoDto saldoDto = cartaoService.obterSaldo(cartao);

        verify(cartaoRepository).findByNumeroCartao(cartao.getNumeroCartao());
        assertNotNull(saldoDto);
        assertEquals(cartao.getSaldoCartao(), saldoDto.saldo());
    }

    @Test
    void obterSaldo_CartaoNaoExistente() {
        when(cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.empty());

        assertThrows(CartaoNaoExistenteException.class, () -> cartaoService.obterSaldo(cartao));

        verify(cartaoRepository).findByNumeroCartao(cartao.getNumeroCartao());
    }

    @Test
    void atualizaDadosCartao_Success() {
        CartaoDto cartaoDto = new CartaoDto(cartao.getId(), cartao.getNumeroCartao(), cartao.getSenhaCartao(), cartao.getSaldoCartao(), cartao.getVersion());
        when(cartaoRepository.save(any(Cartao.class))).thenReturn(cartao);

        assertDoesNotThrow(() -> cartaoService.atualizaDadosCartao(cartaoDto));
        verify(cartaoRepository).save(any(Cartao.class));
    }

    @Test
    void atualizaDadosCartao_OptimisticLockingFailure() {
        CartaoDto cartaoDto = new CartaoDto(cartao.getId(), cartao.getNumeroCartao(), cartao.getSenhaCartao(), cartao.getSaldoCartao(), cartao.getVersion());
        when(cartaoRepository.save(any(Cartao.class))).thenThrow(new OptimisticLockingFailureException("Erro de concorrência"));

        assertThrows(OptimisticLockingFailureException.class, () -> cartaoService.atualizaDadosCartao(cartaoDto));
        verify(cartaoRepository).save(any(Cartao.class));
    }

    @Test
    void obterDadosCartao_CartaoExistente() {
        when(cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        Optional<CartaoDto> cartaoDtoOptional = cartaoService.obterDadosCartao(new CartaoDto(null, cartao.getNumeroCartao(), null, null, null));

        verify(cartaoRepository).findByNumeroCartao(cartao.getNumeroCartao());
        assertTrue(cartaoDtoOptional.isPresent());
        assertEquals(cartao.getNumeroCartao(), cartaoDtoOptional.get().numeroCartao());
    }

    @Test
    void obterDadosCartao_CartaoNaoExistente() {
        when(cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.empty());

        Optional<CartaoDto> cartaoDtoOptional = cartaoService.obterDadosCartao(new CartaoDto(null, cartao.getNumeroCartao(), null, null, null));

        verify(cartaoRepository).findByNumeroCartao(cartao.getNumeroCartao());
        assertTrue(cartaoDtoOptional.isEmpty());
    }
}
