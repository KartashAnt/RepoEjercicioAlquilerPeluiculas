package paquetePrincipal;

public class CD extends Producto{
	public static int proximoCodigoLibre=20000;
	
	private String autor;
	private String titulo;
	private double precio;
	public CD(String autor, String titulo, double precio) {
		super();
		this.autor = autor;
		this.titulo = titulo;
		super.asignarCodigo();
		this.precio = precio;
	}
	public CD() {
		super();
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		titulo = titulo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
