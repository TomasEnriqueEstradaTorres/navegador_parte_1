package uF5.practicas.practica1.navegador_parte_1;

public class Visitas {
	
	private String NombreURL;
	private int cantidadVisitas;
	
	//CONSTRUCTOR
	public Visitas(String nombreURL, int cantidadVisitas) {
		this.NombreURL = nombreURL;
		this.cantidadVisitas = cantidadVisitas;
	}

	//SETTER Y GETTER
	public String getNombreURL() {
		return NombreURL;
	}

	public int getCantidadVisitas() {
		return cantidadVisitas;
	}

	public void setNombreURL(String nombreURL) {
		NombreURL = nombreURL;
	}

	public void setCantidadVisitas(int cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}
	

	//METODOS
	@Override
	public String toString() {
		return "\nURL: " + NombreURL + "\nVisitas:" + cantidadVisitas;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
