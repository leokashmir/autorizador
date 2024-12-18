package com.mvp.autorizador.cartao.application.usercase;

import com.mvp.autorizador.cartao.domain.model.Cartao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CriarCartaoUseCaseTest {

    @InjectMocks
    private CriarCartaoUseCase criarCartaoUseCase;


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
}