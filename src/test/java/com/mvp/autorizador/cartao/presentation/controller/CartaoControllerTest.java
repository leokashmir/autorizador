package com.mvp.autorizador.cartao.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvp.autorizador.config.TestSecurityConfig;
import com.mvp.autorizador.cartao.application.service.CartaoService;
import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.cartao.shared.exception.CartaoJaExistenteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartaoController.class)
@Import(TestSecurityConfig.class)
class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaoService cartaoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Should successfully create a new card")
    void shouldCreateCartaoSuccessfully() throws Exception {
        CartaoDto cartaoDto = new CartaoDto(null, "1234567890123456", "1234", null, null);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should fail to create a card when card already exists")
    void shouldFailWhenCartaoAlreadyExists() throws Exception {
        CartaoDto cartaoDto = new CartaoDto(null, "1234567890123456", "1234", null, null);

        doThrow( CartaoJaExistenteException.builder().numeroCartao("1234567890123456").senhaCartao("1234").build())
                .when(cartaoService).criarCartao(Mockito.any(Cartao.class));

        var json = objectMapper.writeValueAsString(cartaoDto);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("{\"numeroCartao\":\"1234567890123456\",\"senhaCartao\":\"1234\"}"));

    }

    @Test
    @DisplayName("Should fail to create a card when input validation fails")
    void shouldFailDueToValidationErrors() throws Exception {
        CartaoDto invalidCartaoDto = new CartaoDto(null, "", "", null, null);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCartaoDto)))
                .andExpect(status().isBadRequest());
    }
}