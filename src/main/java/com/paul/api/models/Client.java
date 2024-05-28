package com.paul.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clientes")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unsigned
    @Column(name = "id_cliente")
    private Long id;
    @Column(name = "contraseña", length = 6, nullable = false)
    private String password;
    @Column(name = "estado", nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personaId", referencedColumnName = "id_persona")
    private Person person;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> account;
}
