package com.mvp.autorizador.cartao.application.service;

import com.mvp.autorizador.cartao.application.usercase.CriarCartaoUseCase;
import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.repository.CartaoRepositoryImpl;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.cartao.presentation.dto.SaldoDto;
import com.mvp.autorizador.cartao.shared.exception.CartaoNaoExistenteException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartaoService {

    private  CriarCartaoUseCase criarCartaoUseCase;
    private CartaoRepositoryImpl cartaoRepository;

    public CartaoDto criarCartao(Cartao cartao) {
        criarCartaoUseCase.existeCartao(cartao.getNumeroCartao());
        criarCartaoUseCase.adicionaSaldo(cartao);
        var cartaoSalvo = criarCartaoUseCase.save(cartao);
        return new CartaoDto(cartaoSalvo.getNumeroCartao(), cartaoSalvo.getSenhaCartao()) ;
    }

    public SaldoDto obterSaldo(Cartao cartao)  {
        var cartaoConsultado = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());
        var retorno = cartaoConsultado.orElseThrow(CartaoNaoExistenteException::new);
        return new SaldoDto(retorno.getSaldoCartao()) ;
    }
}
