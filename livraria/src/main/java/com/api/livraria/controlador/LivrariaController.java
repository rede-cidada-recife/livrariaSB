package com.api.livraria.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.livraria.entidade.Livro;
import com.api.livraria.servico.LivrariaService;

@RestController
@RequestMapping("/api")
public class LivrariaController {

    private LivrariaService livrariaWS;

    @PostMapping("/livraria/")
    @CrossOrigin("*")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String cadastrar(@RequestBody Livro livro) {
        
        if(livroEhValido(livro, true)){
           var livroCriado = livrariaWS.cadastrarLivro(livro);
           if(livroCriado != null && livroCriado.getId() > 0){
                return String.format("Livro cadastrado com sucesso.", HttpStatus.CREATED);
           }
        }

       return String.format("Não foi possível cadastrar livro. Verificar os dados enviados.", HttpStatus.BAD_REQUEST);
        
    }

    @DeleteMapping("/livraria/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String excluir(@PathVariable Long id) {
        
        livrariaWS.excluirLivroPorId(id);
        Livro livro = livrariaWS.buscaLivroPorId(id);
        if(livro == null || livro.getId() <= 0){
            return String.format("Livro excluído com sucesso.", HttpStatus.OK);
        }
        
        return String.format("Não foi possível excluir o livro. Verificar os dados enviados.", HttpStatus.SERVICE_UNAVAILABLE);
        
    }

    @GetMapping("/livraria/all")
    public List<Livro> buscaLivros() {
        return livrariaWS.buscarTodosLivros();
    }

    @GetMapping("/livraria/{id}")
    public Livro buscaLivroPorId(@PathVariable Long id) {
        Livro livro = livrariaWS.buscaLivroPorId(id);
        if (livro != null && livro.getId() > 0) {
            return livro;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado. Verificar os dados enviados.");
    }

    @PutMapping("/livraria/{id}")
    public Livro alterar(@RequestBody Livro livro, @PathVariable Long id) {
        
        if(livroEhValido(livro, false)){

            Livro livroAlterado = livrariaWS.alterarLivro(id, livro);
            if (livroAlterado != null && livroAlterado.getId() == id) {
                return livroAlterado;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar os dados do livro. Verificar os dados enviados.");
    }


    private boolean livroEhValido(Livro livro, Boolean ehCadastro) {

        if (livro == null) {
            return false;
        }

        if (ehCadastro && livro.getId() > 0) {
            return false;
        }
        
        if (livro.getTitulo().trim().length() <= 0) {
            return false;
        }

        if (livro.getAutorPrincipal().trim().length() <= 0) {
            return false;
        }

        if (livro.getEditora().trim().length() <= 0) {
            return false;
        }

        if (livro.getAnoPublicacao().trim().length() <= 0) {
            return false;
        }

        return true;
    }
    
}
