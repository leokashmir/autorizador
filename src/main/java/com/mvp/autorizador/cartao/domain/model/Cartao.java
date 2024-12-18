package com.mvp.autorizador.cartao.domain.model;




import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Builder @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartao")
public class Cartao {


    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Long id;

    @Column(name = "NUM_CARTAO", length = 16, nullable = false, unique = true)
    String numeroCartao;

    @Column(name = "SENHA_CARTAO", nullable = false)
    String senhaCartao;

    @Column(name = "SALDO_CARTAO", precision = 10, scale = 2, nullable = false)
    BigDecimal saldoCartao;


}