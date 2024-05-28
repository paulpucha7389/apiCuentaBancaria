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
@Table(name = "Personas")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unsigned
    @Column(name = "id_persona")
    private Long id;
    @Column(name = "nombre", length = 60, nullable = false)
    private String name;
    @Column(name = "genero", length = 1, nullable = false)
    private Character genre;
    @Column(name = "edad", length = 3, nullable = false)
    private Short age;
    @Column(name = "identificacion", length = 13, unique = true, nullable = false)
    private String identify;
    @Column(name = "direccion", nullable = false, length = 150)
    private String address;
    @Column(name = "telefono", length = 10)
    private String phone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> client;
}
