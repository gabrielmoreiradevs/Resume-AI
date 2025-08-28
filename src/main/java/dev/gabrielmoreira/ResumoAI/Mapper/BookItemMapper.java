package dev.gabrielmoreira.ResumoAI.Mapper;

import dev.gabrielmoreira.ResumoAI.DTO.BookItemDTO;
import dev.gabrielmoreira.ResumoAI.Model.Bookitem;
import org.springframework.stereotype.Component;

@Component
public class BookItemMapper {

    public Bookitem map(BookItemDTO bookItemDTO) {
        Bookitem bookitem = new Bookitem();
        bookitem.setId(bookItemDTO.getId());
        bookitem.setTitulo(bookItemDTO.getTitulo());
        bookitem.setAutor(bookItemDTO.getAutor());
        bookitem.setGenero(bookItemDTO.getGenero());
        bookitem.setEditora(bookItemDTO.getEditora());
        return bookitem;
    }

    public BookItemDTO map(Bookitem bookitem) {
        BookItemDTO bookItemDTO = new BookItemDTO();
        bookItemDTO.setId(bookitem.getId());
        bookItemDTO.setTitulo(bookitem.getTitulo());
        bookItemDTO.setAutor(bookitem.getAutor());
        bookItemDTO.setGenero(bookitem.getGenero());
        bookItemDTO.setEditora(bookitem.getEditora());
        return bookItemDTO;
    }
}
