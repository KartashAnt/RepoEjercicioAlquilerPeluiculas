package paquetePrincipal;

public class Producto {
	private int codigo;
	private String titulo;
	public Producto(String titulo) {
		super();
		this.titulo=titulo;
	}

	public void asignarCodigo() {
		if(this instanceof Pelicula) {
			this.codigo=Pelicula.proximoCodigoLibre;
			Pelicula.proximoCodigoLibre++;
		}
		else {
			this.codigo=CD.proximoCodigoLibre;
			CD.proximoCodigoLibre++;
		}
	}
	
	
	@Override
	public String toString() {
		return this.codigo+" "+this.titulo;
	}

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
