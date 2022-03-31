package paquetePrincipal;

public class Pelicula extends Producto{
	public static int proximoCodigoLibre=10000;
	private TipoPelicula tipo;
	private String titulo;
	
	public Pelicula(TipoPelicula tipo, String titulo) {
		super();
		this.tipo = tipo;
		super.asignarCodigo();
		this.titulo = titulo;
	}

	public TipoPelicula getTipo() {
		return tipo;
	}

	public void setTipo(TipoPelicula tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


}
