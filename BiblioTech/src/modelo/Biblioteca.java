package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Gestiona el catálogo de libros.
 *
 * @Author Andrés Rosado
 */
public class Biblioteca implements Serializable {
	private static final String ERROR_ACCEDER = "Error crítico al acceder al catálogo";
	private static final long serialVersionUID = 1L;
	private String nombreBiblio;
	private final Libro[] catalogo;
	private int numLibros;
	private static final int MAX_LIBROS = 100;


	public Biblioteca(final String nombre) {
		this.nombreBiblio = nombre;
		catalogo = new Libro[MAX_LIBROS];
		this.numLibros = 0;
	}

	public void guardarEstado(final String ruta) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
			out.writeObject(this);
		} catch (final IOException e) {
			throw new IOException(ERROR_ACCEDER); // Literal repetido
		}
	}

	public static Biblioteca cargarEstado(final String ruta) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
			return (Biblioteca) in.readObject();
		} catch (final IOException e) {
			throw new IOException(ERROR_ACCEDER); // Literal repetido
		}
	}

	public boolean agregarLibro(final String isbn, final String titulo, final String autor) {
		if (this.numLibros >= MAX_LIBROS) {
			return false;
		}
		this.catalogo[numLibros] = new Libro(isbn, titulo, autor);
		numLibros++;
		return true;
	}

	public void prestarLibro(final String isbn) {
		final Libro l = buscarPorISBN(isbn);
		if (l != null) {
			l.prestar();
		}
	}

	public void devolverLibro(final String isbn) {
		final Libro l = buscarPorISBN(isbn);
		if (l != null) {
			l.devolver();
		}
	}

	public boolean existeLibro(final String isbn) {
		return (buscarPorISBN(isbn) != null);
	}

	private Libro buscarPorISBN(final String isbn) {
		for (int i = 0; i < this.numLibros; i++) {
			if (catalogo[i].getIsbn().equals(isbn)) {
				return catalogo[i];
			}
		}
		return null;
	}

	public String mostrarCatalogo() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.numLibros; i++) {
			sb.append(catalogo[i].toString()).append("\n");
		}
		return sb.toString();
	}

	public String getNombreBiblio() {
		return nombreBiblio;
	}

	public void setNombreBiblio(final String n) {
		this.nombreBiblio = n;
	}
}