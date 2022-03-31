package paquetePrincipal;

public class Producto {
	private int codigo;
	
	public Producto() {
		super();
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
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
