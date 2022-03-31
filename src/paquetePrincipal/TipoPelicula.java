package paquetePrincipal;

public enum TipoPelicula {
	NOVEDAD,SEMINOVEDAD,ANTIGUO;

	public static TipoPelicula nuevoTipo(String valor) {
		while(true) {
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
