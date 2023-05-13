package com.api.livraria.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String autorPrincipal;

    @Column(nullable = true, length = 300)
    private String autorSecundario;

    @Column(nullable = false, length = 250)
    private String editora;

    @Column(nullable = false, length = 4)
    private String anoPublicacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorPrincipal() {
        return autorPrincipal;
    }

    public void setAutorPrincipal(String autorPrincipal) {
        this.autorPrincipal = autorPrincipal;
    }

    public String getAutorSecundario() {
        return autorSecundario;
    }

    public void setAutorSecundario(String autorSecundario) {
        this.autorSecundario = autorSecundario;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }


    public Livro() {
    }

    @Override
    public String toString() {
        return "Livro [ " + titulo + " - Autor Principal: " + autorPrincipal + " - Editora: "+ editora + " - Ano publicação: " + anoPublicacao + " ]";
    }
    
}
