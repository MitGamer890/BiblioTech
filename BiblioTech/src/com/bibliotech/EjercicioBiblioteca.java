package com.bibliotech;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal con el menú de la aplicación.
 * 
 * @Author Andrés Rosado
 */
public class EjercicioBiblioteca {
	static Scanner teclado = new Scanner(System.in);

	public static int mostrarMenu() {
		System.out.println("\n    GESTIÓN BIBLIOTECA ");
		System.out.println("========================== ");
		System.out.println("1. Crear biblioteca");
		System.out.println("2. Registrar nuevo libro");
		System.out.println("3. Prestar libro");
		System.out.println("4. Devolver libro");
		System.out.println("5. Consultar catálogo");
		System.out.println("6. Guardar en disco");
		System.out.println("7. Cargar desde disco");
		System.out.println("8. Salir");
		int opcion = teclado.nextInt();
		teclado.nextLine();
		return opcion;
	}

	public static String pedirISBN(Biblioteca biblio) {
		System.out.println(biblio.mostrarCatalogo());
		String isbn = "";
		do {
			System.out.println("Introduce ISBN (o 'x' para salir):");
			isbn = teclado.nextLine();
		} while (!biblio.existeLibro(isbn) && !isbn.equalsIgnoreCase("x"));
		return isbn;
	}

	public static void main(String[] args) {
		int opcion;
		Biblioteca miBiblio = null;
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				if (miBiblio != null)
					System.out.println("Ya existe una biblioteca activa.");
				else {
					System.out.println("Introduce nombre de la biblioteca:");
					String nom = teclado.nextLine();
					miBiblio = new Biblioteca(nom);
				}
				break;
			case 2:
				if (miBiblio == null) {
					System.out.println("BIBLIOTECA NO CREADA");
					break;
				}
				String isbn, titulo, autor;
				do {
					System.out.println("Introduce ISBN:");
					isbn = teclado.nextLine();
					if (miBiblio.existeLibro(isbn))
						System.out.println("ISBN duplicado.");
				} while (miBiblio.existeLibro(isbn));
				System.out.println("Introduce título:");
				titulo = teclado.nextLine();
				System.out.println("Introduce autor:");
				autor = teclado.nextLine();
				if (miBiblio.agregarLibro(isbn, titulo, autor))
					System.out.println("Libro agregado correctamente.");
				break;
			case 3:
				if (miBiblio == null) {
					System.out.println("BIBLIOTECA NO CREADA");
					break;
				}
				String codigo = pedirISBN(miBiblio);
				if (miBiblio.existeLibro(codigo))
					miBiblio.prestarLibro(codigo);
				break;
			case 4:
				if (miBiblio == null) {
					System.out.println("BIBLIOTECA NO CREADA");
					break;
				}
				String codigoDev = pedirISBN(miBiblio);
				if (miBiblio.existeLibro(codigoDev))
					miBiblio.devolverLibro(codigoDev);
				break;
			case 5:
				if (miBiblio == null) {
					System.out.println("BIBLIOTECA NO CREADA");
					break;
				}
				System.out.println(miBiblio.mostrarCatalogo());
				break;
			case 6:
				if (miBiblio == null) {
					System.out.println("BIBLIOTECA NO CREADA");
					break;
				}
				try {
					miBiblio.guardarEstado("./catalogo.dat");
					System.out.println("Guardado.");
				} catch (Exception e) {
					System.out.println("Error al guardar.");
				}
				break;
			case 7:
				try {
					miBiblio = Biblioteca.cargarEstado("./catalogo.dat");
					System.out.println("Cargado.");
				} catch (Exception e) {
					System.out.println("Error al cargar.");
				}
				break;
			}
		} while (opcion != 8);
	}
}