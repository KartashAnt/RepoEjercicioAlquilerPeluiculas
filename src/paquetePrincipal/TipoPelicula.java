package paquetePrincipal;
import java.util.Scanner;
public enum TipoPelicula {
	NOVEDAD(1,3),SEMINOVEDAD(2,2),ANTIGUO(4,1);
	private int dias;
	private double precio;
	
	private TipoPelicula(int dias, double precio) {
		this.dias = dias;
		this.precio = precio;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public static TipoPelicula nuevoTipo(Scanner sc) {
		while(true) {
			System.out.println("Introduzca el tipo de la pelicula(novedad,seminovedad o antiguo):");
			String valor=sc.nextLine();
			for (TipoPelicula tipo : TipoPelicula.values()) {
				if(tipo.name().equalsIgnoreCase(valor)) {
					System.out.println("Tipo valido");
					return tipo;
				}
			}
			System.out.println("Valor invalido");
		}	
	}
}
