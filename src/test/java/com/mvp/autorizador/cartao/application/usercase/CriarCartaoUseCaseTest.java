
package com.mvp.autorizador.cartao.application.usercase;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.repository.CartaoRepositoryImpl;
import com.mvp.autorizador.cartao.shared.exception.CartaoJaExistenteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarCartaoUseCaseTest {

    @InjectMocks
    private CriarCartaoUseCase criarCartaoUseCase;

    @Mock
    private CartaoRepositoryImpl repository;


    @Test
    void deveAdicionarSaldoCorretamente() {

        Cartao cartao = Cartao.builder()
                .numeroCartao("1234567812345678")
                .senhaCartao("1234")
                .saldoCartao(BigDecimal.ZERO)
                .build();

        criarCartaoUseCase.adicionaSaldo(cartao);

        assertNotNull(cartao.getSaldoCartao());
        assertEquals(new BigDecimal("500.00"), cartao.getSaldoCartao());
    }

    @Test
    void deveLancarExcecaoQuandoCartaoExistir() {
        String numeroCartao = "1234567812345678";
        Cartao cartaoMock = Cartao.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao("1234")
                .saldoCartao(new BigDecimal("500.00"))
                .build();

        when(repository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartaoMock));
        assertThrows(CartaoJaExistenteException.class, () -> criarCartaoUseCase.existeCartao(numeroCartao));
    }

    @Test
    void naoDeveLancarExcecaoQuandoCartaoNaoExistir() {
        String numeroCartao = "1234567812345678";

        when(repository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> criarCartaoUseCase.existeCartao(numeroCartao));
    }
}

