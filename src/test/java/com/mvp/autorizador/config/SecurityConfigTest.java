package com.mvp.autorizador.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;



    @Test
    void acessarComCredenciasInvaiiadas() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("username").password("wrongpassword"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    void acessarComCredenciasValidas() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/cartoes")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("username", "password")))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void tentarAcessarSemCredenciasInvaiiadas() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockMvc.perform(get("/cartoes"))
                .andExpect(status().isUnauthorized());
    }
}
