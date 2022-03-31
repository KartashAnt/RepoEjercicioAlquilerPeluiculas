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
	
	@Override
	public String toString() {
		if(this.diasAlquiler<=0) {
			return super.toString() + " - PELICULA - " + tipo.name();
		}
		else {
			return super.toString() + " - PELICULA - " + tipo.name() + " todavia lo quedan " + diasAlquiler + " dias de alquiler";
		}
	}

	public TipoPelicula getTipo() {
		return tipo;
	}

	public void setTipo(TipoPelicula tipo) {
		this.tipo = tipo;
	}


}
