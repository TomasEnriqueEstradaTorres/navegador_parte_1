package uF5.practicas.practica1.navegador_parte_1;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
 
import static javafx.concurrent.Worker.State.FAILED;
 
public class MainPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
 
    private final JPanel panel = new JPanel(new BorderLayout()); //sera el la pantalla entera
    private final JLabel lblStatus = new JLabel(); 
    
    //Botones
    private final JButton botonAtras = new JButton(new ImageIcon("atras24.png"));
    private final JButton botonAdelante = new JButton(new ImageIcon("adelante24.png"));
    private final JButton botonIr = new JButton(new ImageIcon("ir24.png")); // Boton de ir
    
    private final JButton agregarFavoritos = new JButton(new ImageIcon("favorito24.png"));
    private final JButton mostrarFavoritos = new JButton(new ImageIcon("mostrar24.png"));
    private final JButton eliminarFavoritos = new JButton(new ImageIcon("eliminar24.png"));
    private final JButton mostrarHistorial = new JButton(new ImageIcon("historial24.png"));
    
    private final JTextField txtURL = new JTextField(); // esto donde se ingresa la pagina web
    private final JProgressBar progressBar = new JProgressBar();
    
    private Navegador navegador =  new Navegador();
 
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //CONSTRUCTOR		
    public MainPrincipal() {
        super();
        initComponents();
    }
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
   
    private void initComponents() {
        createScene();

        // esto es para cargar la url predefinida cuando se da enter y para el boton 'Ir' => Opcion 1
        ActionListener ir = new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) {
                loadURL(txtURL.getText()); // obtendra el texto ingresado y lo cargara - URL
                navegador.anarA(txtURL.getText());
            }
        };
        botonIr.addActionListener(ir);// es el boton de 'Ir'
        txtURL.addActionListener(ir); // esto donde se ingresa la pagina web
        txtURL.setForeground(Color.blue); // cambia el color de la letra
        botonIr.setToolTipText("Ir a la pagina"); // muestra un mensaje cuandos se para el raton sobre el boton
        
        
        // esto es para el boton de 'Atras' => Opcion 2
        ActionListener alBotonAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				loadURL(navegador.enrere());
			}
		};
		 botonAtras.addActionListener(alBotonAtras);
		 botonAtras.setToolTipText("Ir atras");
	     

		// esto es para el boton de 'Adelante' => Opcion 3
        ActionListener alBotonAdelante = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				loadURL(navegador.endavant());
			}
		};
		botonAdelante.addActionListener(alBotonAdelante);
		botonAdelante.setToolTipText("Ir adelante");

		
		// esto es para el boton de 'favoritos' , para agregar => Opcion 4
        ActionListener alAgregarFavoritos = new ActionListener() {
			@Override //falta terminar las opciones de aceptar y cancelar
			public void actionPerformed(ActionEvent e) {	
				String detalle = JOptionPane.showInputDialog(MainPrincipal.this, txtURL.getText(), "Agregar Pagina a Favoritos", 3);
				navegador.afegirPreferit(txtURL.getText(), detalle);
				JOptionPane.showMessageDialog(MainPrincipal.this, txtURL.getText(), "Pagina Guardada", 1);
			}
		};
		agregarFavoritos.addActionListener(alAgregarFavoritos);
		agregarFavoritos.setToolTipText("Agregar a favoritos");
		
		
		// esto es para el boton de 'eliminarFavoritos' => Opcion 5
        ActionListener alEliminarFavoritos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				//String detalle = JOptionPane.showInputDialog(parentComponent, message, title, messageType)
				navegador.eliminarPreferits(txtURL.getText());
				JOptionPane.showMessageDialog(MainPrincipal.this, txtURL.getText(), "Pagina borrada de favoritos", 1);
			}
		};
		eliminarFavoritos.addActionListener(alEliminarFavoritos);
		eliminarFavoritos.setToolTipText("Eliminar de favoritos");

		
		// esto es para el boton de 'mostrarFavoritos' => Opcion 6
        ActionListener alMostrarFavoritos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				// A la clase se le pasa un objeto de tipo Navegador para poder ver los favoritos
				VentanaFavoritos ventanaFavoritos = new VentanaFavoritos(navegador);
				ventanaFavoritos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		};
		mostrarFavoritos.addActionListener(alMostrarFavoritos);
		mostrarFavoritos.setToolTipText("Mostrar favoritos");
		
		
		// esto es para el boton de 'mostrarFavoritos' => Opcion 7
        ActionListener alMostrarHistorial = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				VentanaHistorial ventanaHistorial = new VentanaHistorial(navegador);
				ventanaHistorial.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
			}
		};
		mostrarHistorial.addActionListener(alMostrarHistorial);
		mostrarHistorial.setToolTipText("Mostrar Hostorial");
		
		
		//----------------------------------------------------------------
		//----------------------------------------------------------------
        progressBar.setPreferredSize(new Dimension(150, 18));
        progressBar.setStringPainted(true);
 
        //Contenido de botones a la izquierda
        JPanel botonesLeft = new JPanel(new FlowLayout());// se usa esto para ordenar los botones
        botonesLeft.add(botonAtras); //boton de atras
        botonesLeft.add(botonAdelante); // boton de adelante 
        botonesLeft.add(agregarFavoritos); // boton de agregar a favoritos
        botonesLeft.add(mostrarFavoritos);// boton para mostrar favoritos
        botonesLeft.add(eliminarFavoritos); // boton eliminar favorito
       
        //Contenido en el centro
        JPanel botonesCenter = new JPanel(new BorderLayout());// se usa esto para ordenar los botones
        botonesCenter.add(txtURL);  // ingreso de la pagina web
        
        //Contenido de botones a la derecha
        JPanel botonesRight = new JPanel(new FlowLayout());// se usa esto para ordenar los botones
        botonesRight.add(botonIr);  // boton ir
        botonesRight.add(mostrarHistorial); // boton eliminar favorito
        
        //Esto es la barra superior  
        JPanel topBar = new JPanel(new BorderLayout()); // ESTA ES LA BARRA CONTENEDORA PRINCIPAL
        topBar.add(botonesLeft, BorderLayout.WEST); 
        topBar.add(botonesCenter, BorderLayout.CENTER);
        topBar.add(botonesRight, BorderLayout.EAST);
        
        
        // esto es para mostrar la barra inferior en donde esta la barra de progreso
        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);
 
        panel.add(topBar, BorderLayout.NORTH); // muestra la barra superior de botones y para escribir la url
        panel.add(jfxPanel, BorderLayout.CENTER); //muestra el contenido de la pagina web
        panel.add(statusBar, BorderLayout.SOUTH); // muestra la barra de porcentaje de carga de la pagina
        getContentPane().add(panel);  // agrega los componentes al panel
 
        setPreferredSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // cierra la ventana haciendo click en el boton de cerrar
        pack();
    }
 
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
    
    //Esto leera lo ingresado
    public void loadURL(String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String tmp = toURL(url); // esto es 'Ir' a la web
 
                if (tmp == null) {
                    tmp = toURL("http://" + url);
                }
                engine.load(tmp);
            }
        });
    }
 
    // esto es 'Ir' a la pagina web
    private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
            return null;
        }
    }
      
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
    //Esto es el Main principal
      public static void main(String[] args) {
          SwingUtilities.invokeLater(new Runnable() {
   
              @Override
              public void run() {
                  MainPrincipal browser = new MainPrincipal();
                  browser.setVisible(true);
                  browser.loadURL("www.google.com"); // pagina definida por defecto, lee lo ingresado  
              }
          });
      }
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
    
    private void createScene() {
 
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
 
                WebView view = new WebView();
                engine = view.getEngine();
 
                engine.titleProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                MainPrincipal.this.setTitle(newValue);
                            }
                        });
                    }
                });
 
                engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(final WebEvent<String> event) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                lblStatus.setText(event.getData());
                            }
                        });
                    }
                });
 
                engine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                txtURL.setText(newValue);
                            }
                        });
                    }
                });
 
                engine.getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, final Number newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setValue(newValue.intValue());
                            }
                        });
                    }
                });
 
                engine.getLoadWorker()
                        .exceptionProperty()
                        .addListener(new ChangeListener<Throwable>() {
 
                            @Override
                            public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
                                if (engine.getLoadWorker().getState() == FAILED) {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            JOptionPane.showMessageDialog(
                                            panel,
                                            (value != null)
                                            ? engine.getLocation() + "\n" + value.getMessage()
                                            : engine.getLocation() + "\nUnexpected error.",
                                            "Loading error...",
                                            JOptionPane.ERROR_MESSAGE);
                                        }
                                    });
                                }
                            }
                        });
 
                jfxPanel.setScene(new Scene(view));
            }
        });
    }
    
}
