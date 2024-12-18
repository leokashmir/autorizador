package com.mvp.autorizador.cartao.infrastructure.repository;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.adapter.CartaoJpaAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartaoRepositoryImplTest {

    @InjectMocks
    private CartaoRepositoryImpl cartaoRepository;

    @Mock
    private CartaoJpaAdapter cartaoJpaAdapter;

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
    void findByNumeroCartao_CartaoExistente() {
        when(cartaoJpaAdapter.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        Optional<Cartao> result = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());

        verify(cartaoJpaAdapter).findByNumeroCartao(cartao.getNumeroCartao());
        assertTrue(result.isPresent());
        assertEquals(cartao.getNumeroCartao(), result.get().getNumeroCartao());
    }

    @Test
    void findByNumeroCartao_CartaoNaoExistente() {
        when(cartaoJpaAdapter.findByNumeroCartao(cartao.getNumeroCartao())).thenReturn(Optional.empty());

        Optional<Cartao> result = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());

        verify(cartaoJpaAdapter).findByNumeroCartao(cartao.getNumeroCartao());
        assertTrue(result.isEmpty());
    }

    @Test
    void save_Success() {
        when(cartaoJpaAdapter.save(cartao)).thenReturn(cartao);

        Cartao result = cartaoRepository.save(cartao);

        verify(cartaoJpaAdapter).save(cartao);
        assertNotNull(result);
        assertEquals(cartao.getNumeroCartao(), result.getNumeroCartao());
        assertEquals(cartao.getSaldoCartao(), result.getSaldoCartao());
    }
}

