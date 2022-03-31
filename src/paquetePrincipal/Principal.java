package paquetePrincipal;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static ArrayList<Pelicula> peliculas= new ArrayList<Pelicula>();
	static ArrayList<CD> cds= new ArrayList<CD>();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		int dia=1;
		while(true) {
			System.out.println("MENU: DIA " + dia);
			System.out.println("1.- Introducir nuevo producto.\n"
							+ "2.- Eliminar producto\n"
							+ "3.- Ver listado de películas\n"
							+ "4.- Ver listado de CDs\n"
							+ "5.- Alquilar película\n"
							+ "6.- Vender disco\n"
							+ "7.- Ver películas en alquiler\n"
							+ "8.- Ver ganancias\n"
							+ "9.- Pasar al día siguiente");
			int entrada=enteroNoNegativo();
			switch (entrada) {
			case 1:
				introducirProducto();
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
				break;
			case 9:
				
				break;
			default:
				System.out.println("Opción invalida");
				break;
			}
		}
	}
	
	public static void introducirProducto() {
		System.out.println("Introduca tipo de producto(1 para pelicula y 2 para CD):");
		String titulo;
		int prod=enteroNoNegativo();
		switch (prod) {
		case 1:
			TipoPelicula tipo=TipoPelicula.nuevoTipo(sc);
			System.out.println("Titulo:");
			titulo=sc.nextLine();
			System.out.println("Cuantas?");
			for (int cantidad = enteroNoNegativo(); cantidad >0; cantidad--) {
				peliculas.add(new Pelicula(tipo, titulo));
			}
			break;
		case 2:
			System.out.println("Autor:");
			String autor=sc.nextLine();
			System.out.println("Titulo:");
			titulo=sc.nextLine();
			System.out.println("Precio:");
			double precio=decimalNoNegativo();
			System.out.println("Cuantos?");
			for (int cantidad = enteroNoNegativo(); cantidad >0; cantidad--) {
				cds.add(new CD(autor, titulo, precio));
			}
			break;
		default:
			System.out.println("Opción invalida");
			break;
		}
	}
	
	//metodo para que me introducen un int no negativo
	public static int enteroNoNegativo() {
			
			int num = 0;
			boolean listo = false;
			//Hasta el momento que no introducen un int valido
			while (!listo) {
				//hago try catch para estar seguro de que me introducen un int
				try {
					
					num = Integer.parseInt(sc.nextLine());
					//Si  numero es positivo es valido
					if (num >= 0) {
						listo = true;
					}
					//El caso de un numero negativo
					else {
						System.out.println("No aceptamos numeros negativos");
					}
					
				}
				//Error de formato de numero
				catch (NumberFormatException e) {
					System.out.println("Formato de entrada invalido");
				}
				
			}
			
			return num;
			
	}
	//metodo para que me introducen un double no negativo
	public static double decimalNoNegativo() {
		
		double num = 0;
		boolean listo = false;
		//Hasta el momento que no introducen un double valido
		while (!listo) {
			//hago try catch para estar seguro de que me introducen un double
			try {
				
				num = Double.parseDouble(sc.nextLine());
				//Si  numero es positivo es valido
				if (num >= 0) {
					listo = true;
				} 
				//El caso de un numero negativo
				else {
					System.out.println("No aceptamos numeros negativos");
				}
				
			}
			//Error de formato de numero
			catch (NumberFormatException e) {
				System.out.println("Formato de entrada invalido");
			}
			
		}
		
		return num;
		
	}
}
