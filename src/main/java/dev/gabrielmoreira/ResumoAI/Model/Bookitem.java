package dev.gabrielmoreira.ResumoAI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bookitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "editora", nullable = false)
    private String editora;
}
