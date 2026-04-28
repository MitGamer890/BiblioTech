package modelo;

import java.io.Serializable;

/**
 * Representa un libro en la biblioteca.
 * 
 * @Author Andrés Rosado
 */
public class Libro implements Serializable {

	private String isbn;
	private String titulo;
	private String autor;
	private boolean prestado;

	public Libro(String isbn, String titulo, String autor) {
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setPrestado(false);
	}

	public void prestar() {
		this.setPrestado(true);
	}

	public void devolver() {
		this.setPrestado(false);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	@Override
	public String toString() {
		return String.format("%-13s | %-30s | %-20s | %s", getIsbn(), getTitulo(), getAutor(), isPrestado() ? "Prestado" : "Disponible");
	}
}