package com.paul.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cuentas")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long id;
    @Column(name = "numero_cuenta", nullable = false)
    private String numberAccount;
    @Column(name = "tipo_cuenta", nullable = false)
    private String typeAccount;
    @Column (name = "saldo_inicial")
    private Double initialBalance;
    @Column(name = "estado", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id_cliente")
    private Client client;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Transaction> transaction;
}
