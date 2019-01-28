package uF5.practicas.practica1.navegador_parte_1;

public class Favoritos {
	
	private String nombreURL;
	private String descripcion;
	
	//CONSTRUCTOR
	public Favoritos(String nombreURLn) {
		this.nombreURL = nombreURL;
	}
	
	public Favoritos(String nombreURL, String descripcion) {
		this.nombreURL = nombreURL;
		this.descripcion = descripcion;
	}
	
	
	//SETTER Y GETTER
	public String getNombreURL() {
		return nombreURL;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombreURL(String nombreURL) {
		this.nombreURL = nombreURL;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	//METODOS
	@Override
	public String toString() {
		return "\nURL: " + nombreURL + "\nDescripcion: " + descripcion;
	}


	//Estos dos metodos se encargaran de verificar si el objeto en cuention es identico y asi no lo agregagra a la lista.
	@Override
	public int hashCode() {
		// aqui lo que se hace es traducir el codigo del espacio de memoria que por lo general es muy largo a uno
		// que sea mas corto y mas comprencible
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreURL == null) ? 0 : nombreURL.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object object) {
		if (object instanceof Favoritos) { // aqui verifica si el objeto pasado es una instancia del objeto Favoritos
			// Se declara una variable de tipo Favoritos y se realiza el casting del objeto 'object' para que sea ahora de tipo Favoritos y
			//asi se pueda acceder al campo nombreURL  */
			Favoritos favorito = (Favoritos)object; 
			//aqui con this hace referencia al objeto donde se encuentra, que es la clase Favoritos, para asi poder compara el campo
			//nombreURL de este con el que se declaro y se le realizo un casting que es 'libro'  */
			if (this.nombreURL == favorito.nombreURL) {  
				return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
	}
		
	
	
	
	
	

}
