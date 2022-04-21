package paquetePrincipal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Class de un cliente
public class Cliente {
	// int para asignar el proximo numero de un socio
	static int proximoNumSocio = 100;
	// Caracteristicas de un cliente
	private int codigo;
	private String nombre;
	private String apellidos;
	private String dni;

	// Peliculas que un cliente tiene alquilado de momebnto y todas la peliculas
	// alquiladas en algun momento temporal por el cliente
	private ArrayList<Pelicula> alquilado = new ArrayList<>();
	private ArrayList<HashMap<String, String>> historial = new ArrayList<>();

	// Constructor con todos los datos
	public Cliente(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.generarCodigo();
	}

	// Generador de codigo de un cliente
	public void generarCodigo() {
		this.codigo = proximoNumSocio;
		proximoNumSocio++;
	}

	// Metodo para meter una pelicula en la lista de los alquilados
	public void alquilarPeli(Pelicula peli) {
		alquilado.add(peli);
	}

	// Metodo para devolver una pelicula
	public void devolverPeli(int codigo) {
		Pelicula peli=buscarPeli(codigo);
		// Si la pelicula esta alquilada
		if (peli!=null) {
			// Guardo su imagen para el historial
			HashMap<String, String> imagenPelicula = new HashMap<>();
			// Codigo y titulo
			imagenPelicula.put("codigo", Integer.toString(peli.getCodigo()));
			imagenPelicula.put("titulo", peli.getTitulo());
			// Retraso de la devuelta
			int dias = peli.getDiasAlquiler();
			if (dias > 0) {
				imagenPelicula.put("retraso", "entragado con adelantado de " + dias + " dias");
			} else if (dias == 0) {
				imagenPelicula.put("retraso", "entregado");
			} else {
				imagenPelicula.put("retraso", "entregado con retraso de " + (0 - dias) + "dias");
			}
			historial.add(imagenPelicula);
			alquilado.remove(peli);
		} else {
			System.out.println("La pelicula no esta alquilada por el cliente");
		}

	}

	// Metodo para borrar pelicula
	public void borrarPeli(int codigo) {
		Pelicula peli=buscarPeli(codigo);
		// Si la pelicula esta alquilada
		if (peli!=null) {
			// Guardo su imagen para el historial
			HashMap<String, String> imagenPelicula = new HashMap<>();
			// Codigo y titulo
			imagenPelicula.put("codigo", Integer.toString(peli.getCodigo()));
			imagenPelicula.put("titulo", peli.getTitulo());
			// Retraso de la devuelta
			imagenPelicula.put("retraso", "no ha sido devuelto");
		} else {
			System.out.println("La pelicula no esta alquilada por el cliente");
		}
	}

	// Listar peliculas alquiladas
	public void listar(boolean alquilado, boolean historial) {
		// Listar peliculas alquiladas de momento
		if (alquilado) {
			System.out.println("PELICULAS ALQUILADAS POR " + this.nombre);
			for (Pelicula pelicula : this.alquilado) {
				System.out.println(pelicula);
			}
		}
		// listar peliculas alquiladas en el pasado
		if (historial) {
			System.out.println("HISTORIAL DE LAS PELICULAS ALQUILADAS POR " + this.nombre);
			for (int i = 0; i < this.historial.size(); i++) {
				System.out.println(this.historial.get(i).get("codigo") + " - " + this.historial.get(i).get("titulo")
						+ " - " + this.historial.get(i).get("retraso"));
			}
		}
	}

	//Metodo para buscar un peli alquilado
	public Pelicula buscarPeli(int codigo) {
		return alquilado.stream().filter(pelicula -> codigo==pelicula.getCodigo() ).findFirst().orElse(null);
	}
	// El toString de cliente
	@Override
	public String toString() {
		return this.codigo + ": " + this.nombre + " " + this.apellidos + ": DNI : " + this.dni;
	}

	// Getters y Setters basicos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Pelicula> getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(ArrayList<Pelicula> alquilado) {
		this.alquilado = alquilado;
	}

	public ArrayList<HashMap<String, String>> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<HashMap<String, String>> historial) {
		this.historial = historial;
	}

}
