package paquetePrincipal;

import java.util.Scanner;

// Enum para guardar valores posibles de tipo de una pelicula
public enum TipoPelicula {
	// Las peliculas pueden ser de esos tipos
	NOVEDAD(1, 3), SEMINOVEDAD(2, 2), ANTIGUO(4, 1);

	// Cada tipo tiene asignado dias que sigue alquilado y el precio de ese tipo de
	// alquiler
	private int dias;
	private double precio;

	// Constructor de tipo para darle numero de dias y precio de alquiler de ese
	// tipo de las peliculas
	private TipoPelicula(int dias, double precio) {

		this.dias = dias;
		this.precio = precio;

	}

	// Un trozo de codigo para poder elegir un tipo exacto
	public static TipoPelicula nuevoTipo(Scanner sc) {
		// Ese metodo si o si tiene que devolver alguno de los tipos disponibles
		while (true) {
			// Pido introduzirme el tipo de pelicula en forma numerica
			System.out
					.println("Introduzca el tipo de la pelicula(1 para novedad, 2 para seminovedad o 3 para antiguo):");
			// Codigo para obtener un numero entero
			int valor = 0;

			while (true) {

				// Probamos introducir un entero y romper el bucle
				try {
					valor = Integer.parseInt(sc.nextLine());
					break;
				}

				// Error de formato de numero
				catch (NumberFormatException e) {
					System.out.println("Formato de entrada invalido");
				}

			}

			// Buscamos ese tipo de pelicula segun su ordinal de enum
			for (TipoPelicula tipo : TipoPelicula.values()) {
				// Si lo encontramos
				if (tipo.ordinal() == valor - 1) {
					// Lo devolvemos
					System.out.println("Tipo valido");
					return tipo;

				}

			}
			// Si no lo encontramos lo indicamos al usuario

			System.out.println("Valor invalido");

		}

	}

	// Getters y Setters basicos
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
