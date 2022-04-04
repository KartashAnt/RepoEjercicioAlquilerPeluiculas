package paquetePrincipal;

// Clase de la pelicula
public class Pelicula extends Producto {
	// Variable que define el proximo codigo disponible para una pelicula
	public static int proximoCodigoLibre = 10000;

	// Caracteristicas de una pelicula su tipo y cuantos dias le quedan en el
	// alquiler
	private TipoPelicula tipo;
	private int diasAlquiler = 0;

	// Constructor a partir de tipo y titulo, el codigo se autoasigna en el
	// constructor de metodo padre
	public Pelicula(TipoPelicula tipo, String titulo) {

		super(titulo);
		this.tipo = tipo;

	}

	// El metodo para que una pelicula se alquila y devuelva el precio de su alquilo
	public double alquilarse() {

		// Dias se ponen en fución del tipo de la pelicula
		this.diasAlquiler = tipo.getDias();
		// Pinto el mensaje de alquiler
		System.out.println("La película " + super.getTitulo() + " era alquilada por " + this.diasAlquiler + " dias");
		// devuelvo el precio que obtengo en función del tipo
		return this.tipo.getPrecio();

	}

	// Combrueba si la pelicula está en alquiler
	public boolean estaAlquilada() {
		// Si no tiene los dias para quedar en alquiler no esta alquilad
		if (this.diasAlquiler <= 0) {
			return false;
		}
		// Si tiene los dias que tiene que seguir alquilada, pues está alquilada
		else {
			return true;
		}

	}

	// Paso el dia
	public void pasarDia() {
		// Si este peli está en el alquiler lo resto un dia
		if (this.estaAlquilada()) {

			this.diasAlquiler--;
			
			//Indico si la pelicula ha sido devuelta
			if(!this.estaAlquilada()) System.out.println("La pelicula " + super.getTitulo() +" con codigo "+ super.getCodigo() + " ha sido devuelta");
		}

	}

	// toString básico
	@Override
	public String toString() {
		// Si la pelicula no está alquilada pinto solo su codigo, nombre y tipo
		if (!this.estaAlquilada()) {
			return super.toString() + " - PELICULA - " + tipo.name();
		}
		// Si la pelicula esta alquilado pinto su codigo, nombre, tipo y las dias que la
		// quedan en el alquiler
		else {
			return super.toString() + " - PELICULA - " + tipo.name() + " todavia lo quedan " + diasAlquiler
					+ " dias de alquiler";
		}

	}

	// Getters y Setters básicos
	public TipoPelicula getTipo() {
		return tipo;
	}

	public void setTipo(TipoPelicula tipo) {
		this.tipo = tipo;
	}

	public int getDiasAlquiler() {
		return diasAlquiler;
	}

	public void setDiasAlquiler(int diasAlquiler) {
		this.diasAlquiler = diasAlquiler;
	}

}
