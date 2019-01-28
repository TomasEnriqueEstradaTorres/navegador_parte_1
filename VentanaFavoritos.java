package uF5.practicas.practica1.navegador_parte_1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class VentanaFavoritos extends JFrame{

	private static final long serialVersionUID = 1L;
		
	
	//CONSTRUCTOR
	public VentanaFavoritos(Navegador navegador){
		setTitle("Favoritos");                   // colocamos titulo a la ventana
        setSize(700, 500);                       // colocamos tamanio a la ventana (ancho, alto)
        setLocationRelativeTo(null);             // centramos la ventana en la pantalla
        setResizable(false);                     // hacemos que la ventana no sea redimiensionable
        	
        //recibe un objeto de tipo navegador
        LaminaFavorito laminaFavorito = new LaminaFavorito(navegador);
	    add(laminaFavorito); // agrega la lamina a la ventana
	    setVisible(true);// la hace visible	
	}
	
}

//clase para poner la caja de texto y poder ver los favoritos
class LaminaFavorito extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea resultado;//area donde se mostrara los favoritos
	private Set<Favoritos> favoritos = new HashSet<Favoritos>(); // sera para recibir el contenido de favoritos
	private final String titulo = "LISTA DE PAGINAS FAVORITAS";
	private Font font = new Font("Arial", Font.BOLD, 12);// Fuente, estilo y tamaño - Font(String name, int style, int size)
	
	//CONSTRUCTOR
	//
	public LaminaFavorito(Navegador navegador) {
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
		favoritos = (Set<Favoritos>) navegador.veurePreferits();
		for (Favoritos favorito : favoritos) {
			resultado.append("\n" + favorito.toString());
		} 
	}
}






