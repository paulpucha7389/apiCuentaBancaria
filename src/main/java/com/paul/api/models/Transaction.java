package com.paul.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transacciones")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date = new Date();
    @Column(name = "tipo_movimiento", nullable = false)
    private String type;
    @Column(name = "valor", nullable = false)
    private Double value;
    @Column(name = "saldo", updatable = true)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "cuentaId", referencedColumnName = "id_cuenta")
    private Account account;
}

