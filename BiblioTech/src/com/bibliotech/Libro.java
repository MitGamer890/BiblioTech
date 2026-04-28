package com.bibliotech;

import java.io.Serializable;

/**
 * Representa un libro en la biblioteca.
 * 
 * @Author Andrés Rosado
 */
public class Libro implements Serializable {

	public String isbn;
	public String titulo;
	public String autor;
	public boolean prestado;

	public Libro(String isbn, String titulo, String autor) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.prestado = false;
	}

	public void prestar() {
		this.prestado = true;
	}

	public void devolver() {
		this.prestado = false;
	}

	@Override
	public String toString() {
		return String.format("%-13s | %-30s | %-20s | %s", isbn, titulo, autor, prestado ? "Prestado" : "Disponible");
	}
}