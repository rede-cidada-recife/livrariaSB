package com.api.livraria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.livraria.entidade.Livro;

public interface LivrariaRepository extends JpaRepository<Livro, Long> {
    
}
