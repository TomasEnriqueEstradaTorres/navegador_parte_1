package uF5.practicas.practica1.navegador_parte_1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Historial {
	
	private String NombreURL;
	private LocalDate fechaActual;
	private LocalTime hora;
	
	//CONSTRUCTOR
	public Historial(String nombreURL, LocalDate fechaActual, LocalTime hora) {
		this.NombreURL = nombreURL;
		this.fechaActual = fechaActual;
		this.hora = hora;
	}
	
	
	//GETTER Y SETTER
	public String getNombreURL() {
		return NombreURL;
	}

	public LocalDate getFechaActual() {
		return fechaActual;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setNombreURL(String nombreURL) {
		NombreURL = nombreURL;
	}

	public void setFechaActual(LocalDate fechaActual) {
		this.fechaActual = fechaActual;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	//METODOS
	@Override
	public String toString() {
		return "\nURL: " + NombreURL + 
				"\nFecha: " + fechaActual + 
				" - Hora: " + hora.getHour() + ":" + hora.getMinute()+ ":" + hora.getSecond();
	}
	

	
	

}
