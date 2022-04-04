package paquetePrincipal;

//Class de un cliente
public class Cliente {
	// int para asignar el proximo numero de un socio
	static int proximoNumSocio = 100;
	// Caracteristicas de un cliente
	private int codigo;
	private String nombre;
	private String apellidos;
	private String dni;

	// Constructor con todos los datos
	public Cliente(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.generarCodigo();
	}

	// Generador de codigo de un cliente
	public void generarCodigo() {
		this.codigo = proximoNumSocio;
		proximoNumSocio++;
	}

	//Getters y Setters basicos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
