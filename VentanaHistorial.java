package uF5.practicas.practica1.navegador_parte_1;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaHistorial extends JFrame {

	// CONSTRUCTOR
	public VentanaHistorial(Navegador navegador) {
		setTitle("Historial"); // colocamos titulo a la ventana
		setSize(700, 500); // colocamos tamanio a la ventana (ancho, alto)
		setLocationRelativeTo(null); // centramos la ventana en la pantalla
		setResizable(false); // hacemos que la ventana no sea redimiensionable

		// recibe un objeto de tipo navegador
		LaminaHistorial laminaHistorial = new LaminaHistorial(navegador);
		add(laminaHistorial); // agrega la lamina a la ventana
		setVisible(true);// la hace visible
	}

}

//clase para poner la caja de texto y poder ver los favoritos
class LaminaHistorial extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea resultado;//area donde se mostrara los favoritos
	//private Set<Favoritos> historial = new HashSet<Favoritos>(); // sera para recibir el contenido de favoritos
	private ArrayList<Historial> historial = new ArrayList<Historial>();
	private final String titulo = "CATALOGO - HISTORAL";
	private Font font = new Font("Arial", Font.BOLD, 12);// Fuente, estilo y tamaño - Font(String name, int style, int size)
	
	//CONSTRUCTOR
	//
	public LaminaHistorial(Navegador navegador) {
		resultado = new JTextArea(28, 60);// se asigna el tamaño del area
		JScrollPane laminaBarras = new JScrollPane(resultado);// con esto le damos barras de desplazamiento
		resultado.setLineWrap(true);// saltara a la siguiente linea cuando llegue al final
		resultado.setEditable(false);// para que no se pueda modificar lo mostrado
		resultado.setBackground(Color.GREEN);// asigna color de fondo al area de texto
		resultado.setForeground(Color.BLUE); // Color de letra 
		//resultado.setFont(Font.decode( "Arial-BOLD-12" )); // Fuente, estilo y tamaño 
		resultado.setFont(font);// Fuente, estilo y tamaño 
		
		add(laminaBarras);// para agregar al area de texto a la lamina
	
		//Agregara la lista de favoritos a la area de texto
		resultado.append(titulo);
		
		historial = (ArrayList<Historial>) navegador.veureHistorial();;
		for (Historial historia : historial) {
			resultado.append("\n" + historia.toString());
		} 
	}
}