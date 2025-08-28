package dev.gabrielmoreira.ResumoAI.Controller;

import dev.gabrielmoreira.ResumoAI.DTO.BookItemDTO;
import dev.gabrielmoreira.ResumoAI.Model.Bookitem;
import dev.gabrielmoreira.ResumoAI.Service.BookItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(name = "/book")
public class BookItemController {

    private final BookItemService bookItemService;

    public BookItemController(BookItemService bookItemService) {
        this.bookItemService = bookItemService;
    }

    //POST
    @PostMapping("/criar")
    public ResponseEntity<BookItemDTO> criar(@RequestBody BookItemDTO bookitem){
        BookItemDTO salvo = bookItemService.criarLivro(bookitem);

        return ResponseEntity.ok(salvo);
    }

    //GET
    @GetMapping("/listar")
    public List<BookItemDTO> listar(){
        return bookItemService.verLivros();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<BookItemDTO> listarPorId(@PathVariable Long id){
        BookItemDTO ver = bookItemService.verLivrosPorId(id);

        if(ver != null){
            return ResponseEntity.status(HttpStatus.OK).body(ver);
        }

        return ResponseEntity.notFound().build();
    }

    //UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<BookItemDTO> alterar(@PathVariable Long id, @RequestBody BookItemDTO bookitem){
        BookItemDTO livroAlterado = bookItemService.alterarLivrosPorId(id, bookitem);

        if(livroAlterado != null){
            return ResponseEntity.ok(livroAlterado);
        }

        return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        BookItemDTO livro =  bookItemService.verLivrosPorId(id);

        if(livro != null){
            bookItemService.deletarLivroPorId(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Livro Deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id não existe!");
    }

}
