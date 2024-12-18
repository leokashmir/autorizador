package com.mvp.autorizador.cartao.application.service;

import com.mvp.autorizador.cartao.application.usercase.CriarCartaoUseCase;
import com.mvp.autorizador.cartao.domain.model.Cartao;
import com.mvp.autorizador.cartao.infrastructure.repository.CartaoRepositoryImpl;
import com.mvp.autorizador.cartao.presentation.dto.CartaoDto;
import com.mvp.autorizador.cartao.presentation.dto.SaldoDto;
import com.mvp.autorizador.cartao.shared.exception.CartaoNaoExistenteException;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaoService {

    private  CriarCartaoUseCase criarCartaoUseCase;
    private CartaoRepositoryImpl cartaoRepository;

    public CartaoDto criarCartao(Cartao cartao) {
        criarCartaoUseCase.existeCartao(cartao.getNumeroCartao());
        criarCartaoUseCase.adicionaSaldo(cartao);
        var cartaoSalvo = cartaoRepository.save(cartao);
        return new CartaoDto(null,cartaoSalvo.getNumeroCartao(), cartaoSalvo.getSenhaCartao(), null, null) ;
    }

    public SaldoDto obterSaldo(Cartao cartao)  {
        var cartaoConsultado = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());
        var retorno = cartaoConsultado.orElseThrow(CartaoNaoExistenteException::new);
        return new SaldoDto(retorno.getSaldoCartao()) ;
    }

    public void AtualizaDadosCartao(CartaoDto cartaoDto) throws OptimisticLockingFailureException {
        var cartao = getCartao(cartaoDto);
        cartaoRepository.save(cartao);
    }

    public Optional<CartaoDto> obterDadosCartao(CartaoDto cartaoDto) {
        return cartaoRepository.findByNumeroCartao(getCartao(cartaoDto).getNumeroCartao())
                .map(cartaoAtualizado -> new CartaoDto(
                        cartaoAtualizado.getId(),
                        cartaoAtualizado.getNumeroCartao(),
                        cartaoAtualizado.getSenhaCartao(),
                        cartaoAtualizado.getSaldoCartao(),
                        cartaoAtualizado.getVersion()
                ));

    }

    private static Cartao getCartao(CartaoDto cartaoDto) {

        return Cartao.builder()
                .id(cartaoDto.id())
                .numeroCartao(cartaoDto.numeroCartao())
                .senhaCartao(cartaoDto.senha())
                .saldoCartao(cartaoDto.saldo())
                .version(cartaoDto.version())
                .build();
    }
}
