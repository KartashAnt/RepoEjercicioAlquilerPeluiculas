package paquetePrincipal;

public class Pelicula extends Producto{
	public static int proximoCodigoLibre=10000;
	private TipoPelicula tipo;
	private int diasAlquiler=0;
	
	public Pelicula(TipoPelicula tipo, String titulo) {
		super(titulo);
		this.tipo = tipo;
		super.asignarCodigo();
	}

	public TipoPelicula getTipo() {
		return tipo;
	}

	public void setTipo(TipoPelicula tipo) {
		this.tipo = tipo;
	}


}
