package dev.gabrielmoreira.ResumoAI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookItemDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private String editora;
}
