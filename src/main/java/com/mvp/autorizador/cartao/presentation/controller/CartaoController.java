package com.mvp.autorizador.cartao.presentation.controller;


import com.mvp.autorizador.cartao.application.service.CartaoService;
import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.cartao.presentation.dto.SaldoDto;
import com.mvp.autorizador.cartao.shared.exception.CartaoJaExistenteException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cartoes")
@Controller
@AllArgsConstructor
public class CartaoController {


    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<?> cadastrarCartao(@RequestBody final CartaoDto cartaoDto) {

         var cartao =   Cartao.builder()
                        .numeroCartao(cartaoDto.numeroCartao())
                        .senhaCartao(cartaoDto.senha())
                        .build()  ;

        cartaoService.criarCartao(cartao);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<SaldoDto> obterSaldoCartao(@PathVariable("numeroCartao")
                                       final String numeroCartao) throws CartaoJaExistenteException {
       var response = cartaoService.obterSaldo(
                Cartao.builder()
                        .numeroCartao(numeroCartao).build()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
