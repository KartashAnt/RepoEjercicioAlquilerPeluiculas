package paquetePrincipal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Clase Principal donde ejecutamos toda la programa
public class Principal {
	// ArrayLists para peliculas y CDs aparte
	static ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	static ArrayList<CD> cds = new ArrayList<CD>();
	//ArrayList de los clientes
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	// Scanner que uso a las medias de todo la programa
	static Scanner sc = new Scanner(System.in);
	// Ganados
	static double ganadoVenta = 0;
	static double ganadoAlquiler = 0;
	// Dia
	static int dia = 1;

	// Main donde ejecutamos el menu principal
	public static void main(String[] args) {

		// Ejecucion infinita
		while (true) {
			// Pinto el menu
			System.out.println("MENU: DIA " + dia);
			System.out.println(
					"1.- Introducir nuevo producto.\n" + 
					"2.- Dar de alta cliente\n" +
					"3.- Eliminar producto\n" + 
					"4.- Ver listado de peliculas\n" + 
					"5.- Ver listado de CDs\n" +
					"6.- Alquilar pelicula\n" + 
					"7.- Vender disco\n" + 
					"8.- Ver peliculas en alquiler\n" + 
					"9.- Ver ganancias\n" + 
					"10.- Pasar al dia siguiente");
			// Eligimos una opcion
			int entrada = enteroNoNegativo();

			// Opciones de menu
			switch (entrada) {
			// Introducir un producto nuevo
			case 1:
				introducirProducto();
				break;
			case 2:
				darDeAltaCliente();
				break;
			// Eliminar un producto
			case 3:
				eliminarProducto();
				break;
			// Listar peliculas
			case 4:
				listarProductos(true, false);
				break;
			// Listar CDs
			case 5:
				listarProductos(false, true);
				break;
			// Alquilar una pelicula
			case 6:
				alquilarPeliculas();
				break;
			// Vender un disco
			case 7:
				ventaDiscos();
				break;
			// Listar peliculas alquiladas
			case 8:
				if (!listarPeliculas(true)) {
					System.out.println("No hay peliculas alquiladas");
				}
				break;
			// Mostrar ganancias
			case 9:
				mostrarGanancias();
				break;
			// Paso de un dia
			case 10:
				pasarDia();
				break;
			// Opcion invalida
			default:
				System.out.println("Opción invalida");
				break;
			}

		}

	}

	// Introducir un producto nuevo
	public static void introducirProducto() {
		// Preguntamos tipo de producto
		System.out.println("Introduca tipo de producto(1 para pelicula y 2 para CD):");
		String titulo;
		int prod = enteroNoNegativo();

		switch (prod) {
		// Pelicula
		case 1:
			// Preguntamos tipo y titulo de la pelicula
			TipoPelicula tipo = TipoPelicula.nuevoTipo(sc);
			System.out.println("Titulo:");
			titulo = sc.nextLine();
			// Preguntamos numero de las peliculas a introducir
			System.out.println("Cuantas?");
			// Introducimos la cantidad n de las peliculas
			for (int cantidad = enteroNoNegativo(); cantidad > 0; cantidad--) {
				peliculas.add(new Pelicula(tipo, titulo));
			}
			break;
		// CD
		case 2:
			// Preguntamos autor,titulo y precio de un CD
			System.out.println("Autor:");
			String autor = sc.nextLine();
			System.out.println("Titulo:");
			titulo = sc.nextLine();
			System.out.println("Precio:");
			double precio = decimalNoNegativo();
			// Preguntamos numero de los CDs a introducir
			System.out.println("Cuantos?");
			// Introducimos la cantidad n de los CDs
			for (int cantidad = enteroNoNegativo(); cantidad > 0; cantidad--) {
				cds.add(new CD(autor, titulo, precio));
			}
			break;
		// Opcion invalida
		default:
			System.out.println("Opción invalida");
			break;
		}

	}
	
	//Metodo para dar de alta a un cliente
	public static void darDeAltaCliente() {
		
		while(true) {
			
			System.out.println("Nombre: ");
			String nombre=sc.nextLine();
			
			System.out.println("Apellidos: ");
			String apellidos=sc.nextLine();
			
			String dni;
			do {
				System.out.println("DNI: ");
				dni=sc.nextLine();
			} while (!validarDNI(dni));
			
			System.out.println("Eres seguro de datos de usuario");
			System.out.println("Nombre: " +nombre);
			System.out.println("Apellidos: " + apellidos);
			System.out.println("DNI:" + dni);
			System.out.println("(S/N)");
			if(aseguro()) {
				System.out.println("Usuario dado de alta");
				clientes.add(new Cliente(nombre, apellidos, dni));
				return;
			}
			else {
				System.out.println("Vuelva a introducir datos");
			}
			
		}
		
	} 
	// Eliminar un producto
	public static void eliminarProducto() {
		// Si hay al menos alqun producto posible para eliminar
		if (peliculas.size() != 0 || cds.size() != 0) {
			// Listo todos los productos
			listarProductos(true, true);
			// Preguntamos el numero de la pelicula al eliminar
			System.out.println("Cual producto se quiere eliminar: ");
			int eliminado = enteroNoNegativo();
			// Si tratamos de un numero a partir de 20000 - hablamos de un CD, asi que
			// buscamos un disco
			if (eliminado >= 20000) {
				// Buscamos el cd necesario
				CD cd = buscarCD(eliminado);
				// Si existe
				if (cd != null) {
					// Aseguramos de que el usuario quire eliminar ese CD
					System.out.println("Estas seguro que quieres eliminar el CD:");
					System.out.println(cd);
					System.out.println("(S/N)");
					// Si decidimos borrar CD
					if (aseguro()) {
						cds.remove(cd);
						System.out.println("CD borrado");
					}
					// Si decidimos de dejarlo en la sistema
					else {
						System.out.println("Borrado cancelado");
					}

				}
				// Si CD no existe
				else
					System.out.println("Error: CD no existe");
			}
			// Si hablamos de un numero menor de 20000 a partir de 10000 - tratamos de una
			// pelicula
			else if (eliminado >= 10000) {
				// Buscamos esa pelicula
				Pelicula pelicula = buscarPeli(eliminado);
				// Si pelicula existe
				if (pelicula != null) {
					System.out.println("Estas seguro que quieres eliminar la pelicula:");
					System.out.println(pelicula);
					System.out.println("(S/N)");
					// Si decidimos borrar peli
					if (aseguro()) {
						// Si la pelicula que borramos permanesca en el alquiler
						if(pelicula.estaAlquilada()) {
							pelicula.getAlquilador().borrarPeli(pelicula);
						}
						peliculas.remove(pelicula);
						System.out.println("Pelicula borrada");
					}
					// Si decidimos de dejarlo en la sistema
					else {
						System.out.println("Borrado cancelado");
					}

				}
				// Si la pelicula no existe
				else
					System.out.println("Error: Pelicula no existe");

			}
			// Si el numera esta fuera de los rangos de los codigos adecuados, es decir
			// menor de 10000
			else {
				System.out.println("Error: Producto pedido no existe");
			}

		}
		// Si no hay productos vinculados en la sistema
		else {
			System.out.println("No hay productos para eliminar");
		}

	}

	// Metodo para listar pelicula o cds
	public static void listarProductos(boolean listarPeliculas, boolean listarCDs) {
		// Si queremos listar peliculas
		if (listarPeliculas) {
			// Listamos peliculas
			for (Pelicula pelicula : peliculas) {
				System.out.println(pelicula);
			}
			// Si no hay peliculas vinculadas en la programa
			if (peliculas.size() == 0)
				System.out.println("No hay peliculas en la programa");
		}
		// Si queremos listar CDs
		if (listarCDs) {
			// Listamos CDs
			for (CD cd : cds) {
				System.out.println(cd);
			}
			// Si no hay CDs vinculados en la programa
			if (cds.size() == 0)
				System.out.println("No hay discos en la programa");
		}
	}

	// Alquilamos una pelicula
	public static void alquilarPeliculas() {
		//Si hay clientes disponibles
		Cliente cliente;
		if(clientes.size()!=0) {
				listarClientes();
				System.out.println("Seleciona el cliente:");
				cliente=clientePorID(enteroNoNegativo());
				//Si cliente existe
				if(cliente!=null) {
					
				}
				else {
					
				}
		}
		//No hay clinetes disponibles
		else {
			System.out.println("No hay clientes disponibles");
			return;
		}
		// Si hay pelicula no alquiladas en la programa
		if (listarPeliculas(false)) {
			// Preguntamos la pelicula
			System.out.println("Cual pelicula se quiere alquilar:");
			Pelicula pelicula = buscarPeli(enteroNoNegativo());
			// Si la pelicula con ese codigo no existe
			if (pelicula == null) {
				System.out.println("La pelicula no existe");
			}
			// Si la pelicula pedida existe pero ya está alquilada
			else if (pelicula.estaAlquilada()) {
				System.out.println("La pelicula ya está alquilada");
			}
			// Si la pelicula existe y además no esta alquilada
			else {
				// Aseguramos de alquiler
				System.out.println("Quieres alquilar " + pelicula.toString());
				System.out.println("(S/N)");
				if (aseguro()) {
					// Alquilamos la pelicula
					System.out.println("Película alquilada");
					// Acumulamos ganancias
					ganadoAlquiler += pelicula.alquilarse(cliente);
					ganadoAlquiler = Math.round(ganadoAlquiler * 100.0) / 100.0;
				}
				// Cancelamos alquiler
				else
					System.out.println("Alquiler cancelado");
			}

		}
		// Si no hay peliculas alquiladas en la sistema
		else {
			System.out.println("No hay peliculas disponibles");
			return;
		}

	}

	// Metodo para vender un disco
	public static void ventaDiscos() {
		// Si hay CDs en nuestra programa
		if (cds.size() != 0) {
			// Listamos CDs
			listarProductos(false, true);
			// Preguntamos el disco que se quiera vender
			System.out.println("Cual disco quiere vender?");
			CD cd = buscarCD(enteroNoNegativo());
			// Si no existe
			if (cd == null) {
				System.out.println("CD no existe");
			}
			// Si existe
			else {
				// Aseguramos de intencion de venta
				System.out.println("Quieres vender " + cd.toString());
				System.out.println("(S/N)");
				if (aseguro()) {
					System.out.println("CD vendido");
					// Acumulamos ganancias
					ganadoVenta += cd.getPrecio();
					ganadoVenta = Math.round(ganadoVenta * 100.0) / 100.0;
					// Borramos disco
					cds.remove(cd);
				} else {
					// Cancelamos la venta
					System.out.println("Venta cancelada");
				}
			}
		}
		// Si no hay CDs disponibles
		else
			System.out.println("No hay CDs disponibles para venta");

	}

	// Muestra de las ganancias
	public static void mostrarGanancias() {
		// Ganado de alquiler
		System.out.println("Ganado por el alquiler de las peliculas: " + ganadoAlquiler + " \u20AC");
		// Ganado de venta
		System.out.println("Ganado por la venta de los discos: " + ganadoVenta + " \u20AC");
		// Ganado total
		System.out.println("Ganado total: " + Math.round((ganadoAlquiler + ganadoVenta) * 100.0) / 100.0 + " \u20AC");
	}

	// Pasamos el dia
	public static void pasarDia() {
		// Aseguramos que una persona quiera pasar el dia
		System.out.println("Se desea pasar el dia(s/n)?");
		if (aseguro()) {
			// Pasamos el dia de identificador y en los alquileres de las poeliculas
			dia++;
			System.out.println("Pasamos al dia " + dia);
			for (Pelicula pelicula : peliculas) {
				pelicula.pasarDia();
			}
		} else {
			System.out.println("El paso del dia cancelado");
		}
	}

	// Metodo para que me introducen un int no negativo
	public static int enteroNoNegativo() {

		int num = 0;
		boolean listo = false;
		// Hasta el momento que no introducen un int valido
		while (!listo) {
			// Hago try catch para estar seguro de que me introducen un int
			try {

				num = Integer.parseInt(sc.nextLine());
				// Si numero es positivo es valido
				if (num >= 0) {
					listo = true;
				}
				// El caso de un numero negativo
				else {
					System.out.println("No aceptamos numeros negativos");
				}

			}
			// Error de formato de numero
			catch (NumberFormatException e) {
				System.out.println("Formato de entrada invalido");
			}

		}

		return num;

	}

	// Metodo para que me introducen un double no negativo
	public static double decimalNoNegativo() {

		double num = 0;
		boolean listo = false;
		// Hasta el momento que no introducen un double valido
		while (!listo) {
			// Hago try catch para estar seguro de que me introducen un double
			try {

				num = Double.parseDouble(sc.nextLine());
				// Si numero es positivo es valido
				if (num >= 0) {
					listo = true;
				}
				// El caso de un numero negativo
				else {
					System.out.println("No aceptamos numeros negativos");
				}

			}
			// Error de formato de numero
			catch (NumberFormatException e) {
				System.out.println("Formato de entrada invalido");
			}

		}

		return num;

	}

	// Buscamos un CD por su codigo
	public static CD buscarCD(int codigo) {
		for (CD cd : cds) {
			if (cd.getCodigo() == codigo) {
				return cd;
			}
		}
		return null;
	}

	// Buscamos un Peli por su codigo
	public static Pelicula buscarPeli(int codigo) {
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getCodigo() == codigo) {
				return pelicula;
			}
		}
		return null;
	}

	// Listamos las pelicula en funcion de su alquiler
	public static boolean listarPeliculas(boolean alquiladas) {
		boolean disponible = false;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.estaAlquilada() == alquiladas) {
				System.out.println(pelicula);
				disponible = true;
			}
		}
		return disponible;
	}

	// Metodo para asegurar una desicion con s/n
	public static boolean aseguro() {
		char entrada = ' ';
		while (true) {
			try {
				entrada = sc.nextLine().toLowerCase().charAt(0);
				if (entrada == 's' || entrada == 'n')
					break;
				else
					System.out.println("Solo se aceptan letras s/n:");
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				System.out.println("No se aceptan lineas vacias:");
			}
		}
		if (entrada == 's') {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo para validar introducoion del DNI
	public static boolean validarDNI(String dni) {
		// Patron de DNI
		if (Pattern.matches("\\d{8}[A-Z]", dni)) {
			// Busqueda del DNI repetido
			for (int i = 0; i < clientes.size(); i++) {

				if (clientes.get(i).getDni().equals(dni)) {
					System.out.println("ERROR: DNI coincide con DNI de otro cliente");
					return false;
				}

			}
			System.out.println("DNI valido");
			return true;
		}

		System.out.println("ERROR: DNI no valido");
		return false;
	}
	
	// Metodo para listar clientes
	public static void listarClientes() {
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
		
	}
	
	//Mecanismo de busqueda de un cliente por id
	public static Cliente clientePorID(int id) {
		return clientes.stream().filter(cliente -> id==cliente.getCodigo()).findFirst().orElse(null);
	}
}
