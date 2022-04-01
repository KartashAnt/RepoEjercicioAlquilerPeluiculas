package paquetePrincipal;

import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

public class Principal {
	static ArrayList<Pelicula> peliculas= new ArrayList<Pelicula>();
	static ArrayList<CD> cds= new ArrayList<CD>();
	static Scanner sc=new Scanner(System.in);
	static double ganadoVenta=0;
	static double ganadoAlquiler=0;
	static int dia=1;
	public static void main(String[] args) {
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
				eliminarProducto();
				break;
			case 3:
				listar(true, false);
				break;
			case 4:
				listar(false, true);
				break;
			case 5:
				alquilarPeliculas();
				break;
			case 6:
				ventaDiscos();
				break;
			case 7:
				if(!listarPeliculas(true)) {
					System.out.println("No hay peliculas alquiladas");
				}
				break;
			case 8:
				mostrarGanancias();
				break;
			case 9:
				pasarDia();
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
	public static void eliminarProducto() {
		if(peliculas.size()!=0 || cds.size()!=0) {
			listar(true, true);
			System.out.println("Cual producto se quiere eliminar: ");
			int eliminado=enteroNoNegativo();
			if(eliminado>=20000) {
				CD cd=buscarCD(eliminado);
				if(cd!=null) {
					System.out.println("Estas seguro que quieres eliminar el CD:");
					System.out.println(cd);
					System.out.println("Introduzca uno para proceder con el borrado o cualquier otra cosa para rechazar");
					if(sc.nextLine().equals("1")) {
						cds.remove(cd);
						System.out.println("CD borrado");
						return;
					}
					System.out.println("Borrado cancelado");
					return;
				}
			}
			else if(eliminado>=10000) {
				Pelicula pelicula=buscarPeli(eliminado);
				if(pelicula!=null) {
					System.out.println("Estas seguro que quieres eliminar la pelicula:");
					System.out.println(pelicula);
					System.out.println("Introduzca uno para proceder con el borrado o cualquier otra cosa para rechazar");
					if(sc.nextLine().equals("1")) {
						peliculas.remove(pelicula);
						System.out.println("Pelicula borrada");
						return;
					}
					System.out.println("Borrado cancelado");
					return;
				}
			}
			System.out.println("El produto pedido no existe");
		}
		else {
			System.out.println("No hay productos para eliminar");
		}
	}
	public static void listar(boolean listarPeliculas, boolean listarCDs) {
		if(listarPeliculas) {
			for (Pelicula pelicula : peliculas) {
				System.out.println(pelicula);
			}
		}
		if(listarCDs) {
			for (CD cd : cds) {
				System.out.println(cd);
			}
		}
	}
	public static void alquilarPeliculas() {
		if (listarPeliculas(false)) {
			System.out.println("Cual pelicula se quiere alquilar:");
			Pelicula pelicula=buscarPeli(enteroNoNegativo());
			if(pelicula==null) {
				System.out.println("La pelicula no existe");
			}
			else if(pelicula.estaAlquilada()) {
				System.out.println("La pelicula ya está alquilada");
			}
			else {
				ganadoAlquiler+=pelicula.alquilarse();
				ganadoAlquiler=Math.round(ganadoAlquiler*100.0)/100.0;
			}
		}
		else {
			System.out.println("No hay peliculas disponibles");
		}
		
	}
	public static void ventaDiscos() {
		listar(false, true);
		System.out.println("Cual disco quiere vender?");
		CD cd=buscarCD(enteroNoNegativo());
		if(cd==null) {
			System.out.println("CD no existe");
		}
		else {
			System.out.println("Quieres vender " + cd.toString() );
			System.out.println("Introduzca uno para proceder con la venta o cualquier otra cosa para cancelarla");
			if(sc.nextLine().equals("1")) {
				System.out.println("CD vendido");
				ganadoVenta+=cd.getPrecio();
				ganadoVenta=Math.round(ganadoVenta*100.0)/100.0;
				cds.remove(cd);
			}
			else {
				System.out.println("Venta cancelada");
			}
		}
	}
	public static void mostrarGanancias() {
		System.out.println("Ganado por el alquiler de las peliculas: " + ganadoAlquiler + " \u20AC");
		System.out.println("Ganado por la venta de los discos: " + ganadoVenta + " \u20AC");
		System.out.println("Ganado total: " + Math.round((ganadoAlquiler+ganadoVenta)*100.0)/100.0 + " \u20AC");
	}
	public static void pasarDia() {
		System.out.println("Se desea pasar el dia(s/n)?");
		if(aseguro()) {
			dia++;
			System.out.println("Pasamos al dia " + dia);
			for (Pelicula pelicula : peliculas) {
				pelicula.pasarDia();
			}
		}
		else {
			System.out.println("El paso del dia cancelado");
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
	public static CD buscarCD(int codigo) {
		for (CD cd : cds) {
			if(cd.getCodigo()==codigo) {
				return cd;
			}
		}
		return null;
	}
	public static Pelicula buscarPeli(int codigo) {
		for (Pelicula pelicula : peliculas) {
			if(pelicula.getCodigo()==codigo) {
				return pelicula;
			}
		}
		return null;
	}
	public static boolean listarPeliculas(boolean alquiladas) {
		boolean disponible=false;
		for (Pelicula pelicula : peliculas) {
			if(pelicula.estaAlquilada()==alquiladas) {
				System.out.println(pelicula);
				disponible=true;
			}
		}
		return disponible;
	}
	public static boolean aseguro() {
		char entrada=' ';
		while(true) {
			try {
				entrada=sc.nextLine().toLowerCase().charAt(0);
				if(entrada=='s' || entrada=='n') break;
				else System.out.println("Solo se aceptan letras s/n:");
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				System.out.println("No se aceptan lineas vacias:");
			}
		}
		if(entrada=='s') {
			return true;
		}
		else {
			return false;
		}
	}
}
