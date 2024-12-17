package com.mvp.autorizador.transacao.presentation.controller;


import com.mvp.autorizador.transacao.presentation.dto.TransacaoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transacoes")
@Controller
public class TransacaoController {


    @PostMapping
    public ResponseEntity<?> realizaTransacao(@RequestBody TransacaoDto transacao) {

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
