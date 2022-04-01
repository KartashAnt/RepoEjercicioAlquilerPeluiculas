package paquetePrincipal;

// El calse del disco CD
public class CD extends Producto {
	// Variable que define proximo codigo libre para un CD
	public static int proximoCodigoLibre = 20000;

	// Caracteristicas de un CD
	private String autor;
	private double precio;

	// Constructor a partir de autor, precio y titulo. El codigo se auto asigna
	public CD(String autor, String titulo, double precio) {

		super(titulo);
		this.autor = autor;
		// Hago redondeo de precio para asegurarme de que solamente va a tener dos
		// digitos detras de punto
		this.precio = Math.round(precio * 100.0) / 100.0;

	}

	// El toString de codigo, titulo, autor y precio
	@Override
	public String toString() {
		return super.toString() + " - CD - de " + this.autor + " - precio: " + this.precio + " \u20AC";
	}

	// Getters y Setters básicos
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
