package paquetePrincipal;

// Clase para definir las caracteristicas comunes de los productos
public class Producto {
	// Las caracteristicas del cualquier producto
	private int codigo;
	private String titulo;

	// Constructor basico que te deja pasarle el titulo pero el codigo se asigna por
	// el defecto
	public Producto(String titulo) {

		super();
		this.titulo = titulo;
		this.asignarCodigo();

	}

	// El metodo para asignar el codigo en fucion de hijo
	public void asignarCodigo() {
		// Si es una pelicula
		if (this instanceof Pelicula) {

			this.codigo = Pelicula.proximoCodigoLibre;
			Pelicula.proximoCodigoLibre++;

		}
		// Si es un CD
		else {

			this.codigo = CD.proximoCodigoLibre;
			CD.proximoCodigoLibre++;

		}
	}
	
	
	// El toString
	@Override
	public String toString() {

		return this.codigo + " - " + this.titulo;

	}

	// Getters y Setters basicos
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
