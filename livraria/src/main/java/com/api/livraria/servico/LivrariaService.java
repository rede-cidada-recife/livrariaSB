package com.api.livraria.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.livraria.entidade.Livro;
import com.api.livraria.repositorio.LivrariaRepository;

@Service
public class LivrariaService {
    
    @Autowired
    private LivrariaRepository livrariaRepository;

    public Livro cadastrarLivro(Livro novoLivro) {
        return livrariaRepository.save(novoLivro);
    }

    public Livro alterarLivro(Long id, Livro livro) {

        var livroAtual = livrariaRepository.findById(id);
        if(!livroAtual.isEmpty()){
            livro.setId(id);
            return livrariaRepository.save(livro);
        }
        return null;
        
    }

    public List<Livro> buscarTodosLivros(){
        return livrariaRepository.findAll();
    }

    public Livro buscaLivroPorId(Long id) {
        var livro = livrariaRepository.findById(id);
        if(!livro.isEmpty()){
            return livro.get();
        }
        return null;
    }

    public void excluirLivroPorId(Long id) {
        var livro = livrariaRepository.findById(id);
        if(!livro.isEmpty()){
            livrariaRepository.deleteById(id);
        }
    }


}
