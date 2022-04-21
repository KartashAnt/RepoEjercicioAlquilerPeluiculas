package paquetePrincipal;

// Clase de la pelicula
public class Pelicula extends Producto {
	// Variable que define el proximo codigo disponible para una pelicula
	public static int proximoCodigoLibre = 10000;

	// Caracteristicas de una pelicula su tipo y cuantos dias le quedan en el
	// alquiler
	private TipoPelicula tipo;
	private int diasAlquiler = 0;

	private Cliente alquilador;
	// Constructor a partir de tipo y titulo, el codigo se autoasigna en el
	// constructor de metodo padre
	public Pelicula(TipoPelicula tipo, String titulo) {

		super(titulo);
		this.tipo = tipo;

	}

	// El metodo para que una pelicula se alquila y devuelva el precio de su alquilo
	public double alquilarse(Cliente alquilador) {
			
		// Dias se ponen en fucion del tipo de la pelicula
		this.diasAlquiler = tipo.getDias();
		// Pinto el mensaje de alquiler
		System.out.println("La pelicula " + super.getTitulo() + " era alquilada por " + this.diasAlquiler + " dias por " + alquilador.getNombre());
		// devuelvo el precio que obtengo en funcion del tipo
		this.alquilador=alquilador;
		alquilador.alquilarPeli(this);
		return this.tipo.getPrecio();
	}
	
	// El metodo para devolver el precio de multa por los dias retrasados por nuestra 
	// Tambien ese metodo maneja la devuelta de una pelicula
	public double devolverse() {
		if(this.estaAlquilada()) {
			this.alquilador.devolverPeli(super.getCodigo());
		this.alquilador=null;
		double precio=0;
		if(diasAlquiler<0) {
			System.out.println("PELICULA HA SIDO DEVUELTA CON RETRASO");
			precio=Math.round(diasAlquiler*tipo.getPrecioPorDiaDeRetraso()*-100.0)/100.0;
			System.out.println("El cliente ha sido multado por " +  precio + " euros");
		}
		else {
			System.out.println("PELICULA HA SIDO DEVUELTA SIN RETRASO");
		}
		return precio;
		}
		else {
			System.out.println("LA PELICULA NO ESTA ALQUILADA");
			return 0;
		}
	}

	// Combrueba si la pelicula esta en alquiler
	public boolean estaAlquilada() {
		// Si no tiene los dias para quedar en alquiler no esta alquilad
		if (this.alquilador == null) {
			return false;
		}
		// Si tiene los dias que tiene que seguir alquilada, pues esta alquilada
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

	// toString basico
	@Override
	public String toString() {
		// Si la pelicula no esta alquilada pinto solo su codigo, nombre y tipo
		if (!this.estaAlquilada()) {
			return super.toString() + " - PELICULA - " + tipo.name();
		}
		// Si la pelicula esta alquilado pinto su codigo, nombre, tipo y las dias que la
		// quedan en el alquiler
		else {
			if(diasAlquiler<0) {
				return super.toString() + " - PELICULA - " + tipo.name() + " se retrasa " + (diasAlquiler*-1)
						+ " dias en devolver - ALQUILADA POR CLIENTE " + alquilador.getCodigo() ;
			}
			else {
				return super.toString() + " - PELICULA - " + tipo.name() + " todavia lo quedan " + diasAlquiler
					+ " dias de alquiler - ALQUILADA POR CLIENTE " + alquilador.getCodigo() ;
			}
			
		}

	}

	// Getters y Setters basicos
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

	public Cliente getAlquilador() {
		return alquilador;
	}

	public void setAlquilador(Cliente alquilador) {
		this.alquilador = alquilador;
	}
	
}
