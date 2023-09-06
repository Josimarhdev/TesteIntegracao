package br.com.lembretes.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pessoas", schema = "public")
public class Pessoas {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome",nullable = false , length = 100)
    private String nome;

    @Getter @Setter
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Lembretes> lembretes;


    public Pessoas() {
    }

    public Pessoas(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
