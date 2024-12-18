package com.mvp.autorizador.transacao.presentation.controller;


import com.mvp.autorizador.transacao.application.service.TransacaoService;
import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transacoes")
@Controller
@AllArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> realizaTransacao(@RequestBody @Valid  TransacaoDto transacao) {

        transacaoService.autorizaTransacao(transacao);
        return new ResponseEntity<>( "OK", HttpStatus.CREATED);
    }
}
