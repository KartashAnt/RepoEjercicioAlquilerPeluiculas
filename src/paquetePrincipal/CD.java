package paquetePrincipal;

public class CD extends Producto{
	public static int proximoCodigoLibre=20000;
	
	private String autor;
	private double precio;
	public CD(String autor, String titulo, double precio) {
		super(titulo);
		this.autor = autor;
		super.asignarCodigo();
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return super.toString() +" - CD - de " +this.autor + " - precio: " + this.precio + " euros";
	}

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
