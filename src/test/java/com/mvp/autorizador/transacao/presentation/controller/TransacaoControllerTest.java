package com.mvp.autorizador.transacao.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvp.autorizador.TestSecurityConfig;
import com.mvp.autorizador.transacao.application.service.TransacaoService;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
@Import(TestSecurityConfig.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoService transacaoService;



    @Test
    void realizaTransacao_SuccessfulTransaction_ReturnsCreated() throws Exception {
        TransacaoDto transacaoDto = new TransacaoDto("1234567891234568", "senha123", BigDecimal.valueOf(500.00));
        doNothing().when(transacaoService).autorizaTransacao(Mockito.any(TransacaoDto.class));

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("OK"));
    }

    @Test
    void realizaTransacao_InvalidRequestBody_ReturnsBadRequest() throws Exception {
        TransacaoDto invalidDto = new TransacaoDto(null, null, BigDecimal.ZERO);

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }


}
