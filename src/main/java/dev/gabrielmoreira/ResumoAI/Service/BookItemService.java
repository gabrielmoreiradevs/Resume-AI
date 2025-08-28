package dev.gabrielmoreira.ResumoAI.Service;

import dev.gabrielmoreira.ResumoAI.DTO.BookItemDTO;
import dev.gabrielmoreira.ResumoAI.Mapper.BookItemMapper;
import dev.gabrielmoreira.ResumoAI.Model.Bookitem;
import dev.gabrielmoreira.ResumoAI.Repository.BookItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookItemService {

    private final BookItemRepository bookItemRepository;
    private final BookItemMapper bookItemMapper;

    public BookItemService(BookItemRepository bookItemRepository, BookItemMapper bookItemMapper) {
        this.bookItemRepository = bookItemRepository;
        this.bookItemMapper = bookItemMapper;
    }

    public BookItemDTO criarLivro(BookItemDTO bookitem){
        Bookitem bookitem1 = bookItemMapper.map(bookitem);
        Bookitem salvo = bookItemRepository.save(bookitem1);

        return bookItemMapper.map(salvo);
    }

    public List<BookItemDTO> verLivros(){
        List<Bookitem> livros = bookItemRepository.findAll();
        return livros.stream()
                .map(bookItemMapper::map)
                .collect(Collectors.toList());
    }

    public BookItemDTO verLivrosPorId(Long id){
        Optional<Bookitem> livro = bookItemRepository.findById(id);
        return livro.map(bookItemMapper::map).orElse(null);
    }

    public BookItemDTO alterarLivrosPorId(Long id, BookItemDTO bookitem){
        Optional<Bookitem> bookitem1 = bookItemRepository.findById(id);

        if(bookitem1.isPresent()){
            Bookitem livro = bookItemMapper.map(bookitem);
            livro.setId(id);
            bookItemRepository.save(livro);
            return bookItemMapper.map(livro);
        }
        return null;
    }

    public void deletarLivroPorId(Long id){
        bookItemRepository.deleteById(id);
    }

}
