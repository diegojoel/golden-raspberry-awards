package br.com.goldenraspberryawards.entities;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Film")
@Getter
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String releaseYear;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;

    public FilmEntity() {}

    public FilmEntity(String releaseYear, String title, String studios, String producers, boolean winner) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }
}