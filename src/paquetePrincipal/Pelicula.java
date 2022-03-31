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
	public double alquilarse() {
		this.diasAlquiler=tipo.getDias();
		System.out.println("La película " + super.getTitulo() + " era alquilada por " + this.diasAlquiler +" dias");
		return this.tipo.getPrecio();
	}
	public boolean estaAlquilada() {
		if(this.diasAlquiler<=0) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public String toString() {
		if(!this.estaAlquilada()) {
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
