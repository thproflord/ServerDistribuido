package com.company.Servidor.Libro;

public class Libro {

    private String autor;
    private String titulo;

    public Libro (String autor, String titulo){
        this.autor = autor;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
