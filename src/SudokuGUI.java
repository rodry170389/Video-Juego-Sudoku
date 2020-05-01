import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.util.Random;

/**
 * Clase SudokuGUI: Clase que crea la interfaz de
 * usuario, implementa la intefaz ActionListner para
 * los eventos de cada boton creado y define métodos
 * para unir la interfaz gráfica con la clase que
 * resuelve el sudoku.
 * @author José Rodrigo Fuentes Ramírez
 * @version	1.0  
 * @see SudokuSolver
 * @see SudokuGUIMini
 * @see ActionListener
 */

public class SudokuGUI implements ActionListener{
	
	/** Constante que limita el número de caracteres*/
	 public static final int MAX_CHARACTERS = 1;
	 
	/**
	   * Atributos que configuran la interfaz que interactuar con
	   * el usuario final.
	   */

	 /** Define la ventana llamada "Sudoku"*/
	 private JFrame frame;
	
	 /** Contenedor que contiene todo de SudokuGUI*/
	 private Container contenedor;
	  
	 /** Nombres de los 7 botones de la interfaz Sudoku*/
	 private final String nombres[] = {"Resolver", "Limpiar", "Aleatorio" ,"Sudoku-Kids", "Ayuda", "Acerca de", "Salir"};
	 
	 /** Botones para la interfaz gráfica "Sudoku"*/
	 private JButton comandos[]= new JButton[7];
	 // private JButton comandos = new JButton(nombres).
	 
	 /** Areas de texto para las 81 casillas del sudoku*/
	 private JTextArea texto[][] = new JTextArea[9][9];
	 // private JTextArea texto = new JTextArea().
	 
	 /** Limitadores de caracteres para cada area de texto de la interaz SudokuGUI*/
	 private LimitedStyledDocument lpd[][] = new LimitedStyledDocument[9][9];
	 // private LymitedStyledDocument lpd = new LimitedStyledDocument(MAX_CHARACTERS).
	 
	 /** Configura la cabecera de la parte izquierda de la Interfaz gráfica*/
	 private JLabel titulo;
	 private JLabel ladoizq;
	 private JLabel ladoder;
	 
	 /** Definimos un panel para contener las 9 regiones de las 81 casillas*/
	 private JPanel sudoku;
	 
	 /** Creamos 9 paneles para contener las 81 casillas de las 9 regiones*/
	 private JPanel pan9gran [] = new JPanel[9];
	 // private JPanel pan9gran = new Jpanel(new GridLayout(3,3,1,1))
	 
	 /** Definimos un panel que contiene los botones el título y las imagenes*/
	 private JPanel eons;
	 
	 /** Definimos otro panel, este contiene solo a los botones*/
	 private JPanel panelcentral;
	 
	 
	 
	 /**
	  * Método que crea la interfaz de usuario. 
	  * No se le pasa ningún parámetro.
	  * Se importan todos los métodos.
	  */
	 public void init(){
		
		// Modificamos la apariencia y la funcionalidad de la ventana. 
	    frame = new JFrame("SudoKu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Convertimos a la ventana en un contenedor
        contenedor = frame.getContentPane();
        
        // Modificamos el titulo.        
        titulo = new JLabel("SudoKu",JLabel.CENTER);
        titulo.setFont(new Font( "Brush Script MT", Font.ITALIC, 70));
        titulo.setForeground(Color.WHITE);
        ladoizq = new JLabel("***************",JLabel.CENTER);
        ladoizq.setForeground(Color.BLACK);
        ladoder = new JLabel("***************",JLabel.CENTER);
        ladoder.setForeground(Color.BLACK);
        
        // Añadimos al panel sudoku una tabla de 9 (Gridlayout) y le damos un color.
        sudoku = new JPanel(new GridLayout(3,3,4,4));
        sudoku.setBackground(Color.BLACK);
        
        // Añadimos a 9 paneles 9 tablas(GridLayout), estas serán cada una de las casillas del sudoku. 
        for(int i = 0; i < pan9gran.length; i++){
            	pan9gran[i] = new JPanel(new GridLayout(3,3,1,1));
            	pan9gran[i].setBackground(Color.DARK_GRAY);
            }
    
        // Inicializamos las areas de texto de los cuadritos pequeños, los limitamos a un carácter y cambiamos su apariencia.
        for(int i = 0; i < texto.length; i++){
        	for(int j = 0; j < texto.length; j++){
        		lpd[i][j] = new LimitedStyledDocument(MAX_CHARACTERS);
        		texto[i][j] = new JTextArea();
        		texto[i][j].setBackground(Color.WHITE);
        		texto[i][j].setDocument(lpd[i][j]);
        		texto[i][j].setFont(new Font("Goudy Stout", Font.ITALIC, 28));
        	}	
        }
         
        // Añadimos los paneles pequeños(representan a cada cuadrito) a las 9 regiones(reprentan a los 9 cuadros grandres)
        for(int i = 0; i < 9; i++){
        	for(int j = 0; j < 9; j++){
        		pan9gran[i].add(texto[i][j]);
        		sudoku.add(pan9gran[i]);
        	}
        }
             
        // Añadimos a panelcentral un GridLayout y le damos color.
        panelcentral = new JPanel(new GridLayout(8,1,0,7));
        panelcentral.setBackground(Color.BLACK);
        
        // Agregamos los respectivos objetos al panel eons
        eons = new JPanel(new BorderLayout());
        eons.add(panelcentral, BorderLayout.CENTER);// metemos panel central en el centro del borderlayout
        eons.add(titulo, BorderLayout.NORTH);
        eons.add(ladoizq, BorderLayout.WEST);
        eons.add(ladoder, BorderLayout.EAST);
        eons.setBackground(Color.BLACK);
         
        //Damos nombres a los botones, los añadimos a su panel correspondiente 
        // y lo mas importante, los asociamos con la interfaz ActionListener.
         
        for(int j = 0; j < comandos.length; j++){
        	comandos[j] = new JButton(nombres[j]);
        	panelcentral.add(comandos[j], BorderLayout.CENTER);
        	comandos[j].setBackground(Color.WHITE);
        	comandos[j].addActionListener(this);
        }
        
        // Añadimos todos los paneles al panel principal.
        contenedor.setLayout(new GridLayout(1,1)); 
        contenedor.add(eons);
        contenedor.add(sudoku);
        
        // Damos el tamaño a la ventana y el lugar, finalmente lo hacemos visible.
        frame.setSize(600,350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
	 }
	 	 
	 /**
	  * Método que obtiene los números ingresados en la interfaz,
	  * cambia a las correctas coordenadas del tablero y los almacena
	  * en un array bidimencional para luego devolverlo al constructor
	  * de SudokuSolver. 
	  * Devuelve un array de String bidimensional.
	  * @return arrayaux guarda las coodenadas del tablero y el valor. 
	  */
	 public String[][]GetValores(){
		
		 // Guarda las coordenadas correctas
		 String coord[][]={
				 {texto[0][0].getText(),texto[0][1].getText(),texto[0][2].getText(),texto[1][0].getText(),texto[1][1].getText(),texto[1][2].getText(),texto[2][0].getText(),texto[2][1].getText(),texto[2][2].getText(),},
				 {texto[0][3].getText(),texto[0][4].getText(),texto[0][5].getText(),texto[1][3].getText(),texto[1][4].getText(),texto[1][5].getText(),texto[2][3].getText(),texto[2][4].getText(),texto[2][5].getText(),},
				 {texto[0][6].getText(),texto[0][7].getText(),texto[0][8].getText(),texto[1][6].getText(),texto[1][7].getText(),texto[1][8].getText(),texto[2][6].getText(),texto[2][7].getText(),texto[2][8].getText(),},
				 {texto[3][0].getText(),texto[3][1].getText(),texto[3][2].getText(),texto[4][0].getText(),texto[4][1].getText(),texto[4][2].getText(),texto[5][0].getText(),texto[5][1].getText(),texto[5][2].getText(),},
				 {texto[3][3].getText(),texto[3][4].getText(),texto[3][5].getText(),texto[4][3].getText(),texto[4][4].getText(),texto[4][5].getText(),texto[5][3].getText(),texto[5][4].getText(),texto[5][5].getText(),},
				 {texto[3][6].getText(),texto[3][7].getText(),texto[3][8].getText(),texto[4][6].getText(),texto[4][7].getText(),texto[4][8].getText(),texto[5][6].getText(),texto[5][7].getText(),texto[5][8].getText(),},
				 {texto[6][0].getText(),texto[6][1].getText(),texto[6][2].getText(),texto[7][0].getText(),texto[7][1].getText(),texto[7][2].getText(),texto[8][0].getText(),texto[8][1].getText(),texto[8][2].getText(),},
				 {texto[6][3].getText(),texto[6][4].getText(),texto[6][5].getText(),texto[7][3].getText(),texto[7][4].getText(),texto[7][5].getText(),texto[8][3].getText(),texto[8][4].getText(),texto[8][5].getText(),},
				 {texto[6][6].getText(),texto[6][7].getText(),texto[6][8].getText(),texto[7][6].getText(),texto[7][7].getText(),texto[7][8].getText(),texto[8][6].getText(),texto[8][7].getText(),texto[8][8].getText(),}
		 };
		 
		 // Guarda la posición en la que se encuentra el valor, y el valor en si mismo para luego devolverlo.
		 String [][]arrayaux = new String [9][9];
		
		 // Comprobamos y guardamos las coordenadas en un array: arrayaux
		 uno: for(int i = 0; i < coord.length; i++){
			 	for(int j = 0; j < coord.length; j++){
			 		if(coord[i][j].equals("1")||coord[i][j].equals("2")||coord[i][j].equals("3")
			 				||coord[i][j].equals("4")||coord[i][j].equals("5")||coord[i][j].equals("6")
			 				||coord[i][j].equals("7")||coord[i][j].equals("8")||coord[i][j].equals("9")){
			 			arrayaux[i][j] = String.valueOf(i)+String.valueOf(j)+coord[i][j];				
			 		}else{
			 			if(coord[i][j].equals("")){
			 				arrayaux[i][j] = String.valueOf(i)+String.valueOf(j)+"0";
			 			}else{
			 				
			 				 // Creamos una ventana para informar del error(carácter no válido)
			 				 JFrame Ayuda = new JFrame("ERROR!!!!");
			 				 Ayuda.setResizable(false);
			 				 Container VenAyuda = Ayuda.getContentPane();
			 				 JTextArea textArea = new JTextArea(5, 10);
			 				 textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
			 				 textArea.setText("Error!! al introducir un car�cter. "+
			 						 		  "Usted est� intentando introducir un simbolo "+
			 						 		  "o una letra.                  Por favor corrijalo!"
			 				 					);
			 				 textArea.setBackground(Color.YELLOW);
			 				 textArea.setLineWrap(true);
			 				 textArea.setWrapStyleWord(true);
			 				 textArea.setEditable(false);
			 				 VenAyuda.add(textArea);
			 				 Ayuda.setSize(210,120);
			 				 Ayuda.setLocationRelativeTo(null);
			 				 Ayuda.setVisible(true);
			 				 break uno; 				
			 			} 					
			 		} 				
			 	}
		 }
		 return arrayaux;
	 }
	
	 /**
	  * Método que muestra el resultado final en la interfaz gráfica y 
	  * no deja que editemos una vez mostrado el resultado.
	  * No recibe ningún parámetro.
	  */
	 public void resultadofinal(){
		
		 // Copiamos en array(fin) la solución del sudoku propuesto
		 String[][] fin = SudokuSolver.paintr;
		
		 // Cambiamos las coordenadas para que lo muestre correctamente.
		 String[][]pantalla ={
				 {fin[0][0],fin[0][1],fin[0][2],fin[1][0],fin[1][1],fin[1][2],fin[2][0],fin[2][1],fin[2][2],},
				 {fin[0][3],fin[0][4],fin[0][5],fin[1][3],fin[1][4],fin[1][5],fin[2][3],fin[2][4],fin[2][5],},
				 {fin[0][6],fin[0][7],fin[0][8],fin[1][6],fin[1][7],fin[1][8],fin[2][6],fin[2][7],fin[2][8],},
				 {fin[3][0],fin[3][1],fin[3][2],fin[4][0],fin[4][1],fin[4][2],fin[5][0],fin[5][1],fin[5][2],},
				 {fin[3][3],fin[3][4],fin[3][5],fin[4][3],fin[4][4],fin[4][5],fin[5][3],fin[5][4],fin[5][5],},
				 {fin[3][6],fin[3][7],fin[3][8],fin[4][6],fin[4][7],fin[4][8],fin[5][6],fin[5][7],fin[5][8],},
				 {fin[6][0],fin[6][1],fin[6][2],fin[7][0],fin[7][1],fin[7][2],fin[8][0],fin[8][1],fin[8][2],},
				 {fin[6][3],fin[6][4],fin[6][5],fin[7][3],fin[7][4],fin[7][5],fin[8][3],fin[8][4],fin[8][5],},
				 {fin[6][6],fin[6][7],fin[6][8],fin[7][6],fin[7][7],fin[7][8],fin[8][6],fin[8][7],fin[8][8],}
		 };
		
		 // Mostramos el resultado y no dejamos que se edite.
		 for(int i =0; i<texto.length; i++){
			 for(int j=0; j<texto.length;j++){
				 texto[i][j].setText(pantalla[i][j]);
				 texto[i][j].setEditable(false);
			 }
		 }
	 }
	 
	 /**
	  * Método para dar funcionalidad al boton "Resolver".
	  * Resuelve el sudoku que queremos que resuelva.
	  * No recibe ningún parámetro.
	  * @exception Exception
	  */
	 public void BotonResolver() throws Exception{
		
		 // Pasamos los valores con el metodo(GetValores)
		 SudokuSolver s;
		 s = new SudokuSolver(GetValores());
		 
		 // Imprimimos en la consola los valores que le pasamos
		 s.display();
		 
		 // Resuelve e imprime el resultado en la consola y en la interfaz gráfica. 
		 if(s.solve()) {
			 s.display();
			 resultadofinal();
		 }else{
			 System.out.println("Intentelo otra vez por favor!");
		 }
	 }
	    
	 /**
	  * Método para dar funcionalidad al boton "Limpiar".
	  * Modifica el texto que esta en la interfaz gráfica
	  * y lo hace editable.
	  * No recibe ningún parámetro.
	  */   
	 public void BotonLimpiar(){
		
		 //Borra todos los números y hace la interfaz grafica editable. 
		 for(int i =0; i<texto.length; i++){
			 for(int j=0; j<texto.length;j++){
				 texto[i][j].setText("");
				 texto[i][j].setEditable(true);
			 }
		 }	
	 }
	 
	 /**
	  * Método para dar funcionalidad al boton "Ayuda".
	  * Crea una nueva ventana en la que salen las
	  * instrucciones para resolver un sudoku.
	  * No recibe ningún parámetro.
	  */   
	 public void BotonAyuda(){
		 
		 // Creamos una ventana para mostrar información que puede ayudar. 
		 JFrame Ayuda = new JFrame("Ayuda");
		 Ayuda.setResizable(false);
		 Container VenAyuda = Ayuda.getContentPane();
		 JTextArea textArea = new JTextArea(5, 10);
		 textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
		 textArea.setText(
				 "SUDOKU 9x9                                                    "+
				 "Completa el tablero (con 9 cuadros) de 81 casillas " +
				 "dispuestas en 9 filas y 9 columnas , rellenando las " +
				 "celdas vacías con los números del 1 al 9 , sin que " +
				 "se repita ninguna cifra en cada fila, ni en cada " +
				 "columna, ni en cada cuadrado.                                       "+
				 "                                                                            "+
				 "SUDOKU-KIDS                                                  "+
				 "Completa el tablero (Con 6 cuadros) de 36 casillas "+
				 "dispuestas en 6 filas y 6 columnas, rellenado las "+
				 "celdas vacías con los números del 1 al 6, sin que "+
				 "se repita ninguna cifra en cada fila, ni en cada "+
				 "columna, ni en cada cuadro"
		 );
		 textArea.setBackground(Color.LIGHT_GRAY);
		 textArea.setLineWrap(true);
		 textArea.setWrapStyleWord(true);
		 textArea.setEditable(false);
		 VenAyuda.add(textArea);
		 Ayuda.setSize(335,335);
		 Ayuda.setLocationRelativeTo(null);
		 Ayuda.setVisible(true);
	 }
	
	 /**
	  * Método para dar funcionalidad al boton "Sudoku-Kids".
	  * Inicializa la interfaz gráfica de sudoku-Kids con 
	  * sus respectivas funcionalidades.
	  * No recibe ningún parámetro.
	  */   
	 public void BotonKids(){
		 
		 // Inicializamos la interfaz de usuario del Sudoku-Kids
		 SudokuGUIMini inter = new SudokuGUIMini();
		 inter.initmini();
	 }
	 
	 /**
	  * Método para dar funcionalidad al boton "Acerca de".
	  * Crea una ventana con información por ejemplo autores, etc...
	  * No recibe ningún parámetro.
	  */   
	 public void BotonAcercade(){
		 
		 // Creamos una ventana para mostrar la información acerca de los autores, la versión, etc...
		 JFrame Acercade = new JFrame("Ayuda");
		 Acercade.setResizable(false);
		 Container Acerca = Acercade.getContentPane();
		 JTextArea textArea = new JTextArea(5, 10);
		 textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
		 textArea.setText(
				 "Creado por:                                 " +
				 "  - José Rodrigo Fuentes Ramírez   " +
				 "Version: 1.0                                " 	 
		 );
		 textArea.setBackground(Color.LIGHT_GRAY);
		 textArea.setLineWrap(true);
		 textArea.setWrapStyleWord(true);
		 textArea.setEditable(false);
		 Acerca.add(textArea);
		 Acercade.setSize(220,120);
		 Acercade.setLocationRelativeTo(null);
		 Acercade.setVisible(true);
	 }
	
	 /**
	  * Método para dar funcionalidad al boton "Aleatorio".
	  * Asigna números aleatoriamente en el tablero, para
	  * luego resolverlo.
	  * No recibe ningún parámetro.
	  */   
	 public void BotonAleatorio(){
		 
		 // Definimos las variables que guardan los números aleatorios.
		 int numero;
		 int numero2;
		 int numero3;
		 
		 // Variable que necesitamos para guardar la variable numero.
		 int guardavalor = 0;
		 
		 // Generamos las filas y las columnas aleatoriamente y luego lo escribimos en la interfaz. 
		 Random rnd = new Random(System.currentTimeMillis());
		 for(int i = 0; i < 9; i++){
			 	 numero = rnd.nextInt(9);
				 numero2 = rnd.nextInt(9);
				 numero3 = guardavalor;
				 numero3 = numero3+1;
				 guardavalor = numero3;
				 texto[numero][numero2].setText(String.valueOf(numero3));		 
		 }
	 }
	
	 /**
	  * Método que implementamos de la interfaz ActionListener.
	  * Escucha todos los eventos de los botones luego inicializa 
	  * sus correspondientes métodos
	  * No recibe ningún parámetro.
	  * @see ActionListener
	  * @exception Exception
	  */   	
	 public void actionPerformed(ActionEvent evento) {
		
		// Elige el método según el botón que pulse el usuario en la interfaz gráfica SudokuGUI. 
		if (evento.getActionCommand().equals("Resolver")){
			try {
				BotonResolver();
			}catch(Exception e){
				e.printStackTrace();
			}
			}else{
				if(evento.getActionCommand().equals("Limpiar")){
					BotonLimpiar();
				}else{
					if(evento.getActionCommand().equals("Ayuda")){
						BotonAyuda();
					}else{
						if(evento.getActionCommand().equals("Sudoku-Kids")){
							BotonKids();
						}else{
							if(evento.getActionCommand().equals("Acerca de")){
								BotonAcercade();
							}else{
								if(evento.getActionCommand().equals("Aleatorio")){
									BotonLimpiar();
									BotonAleatorio();
								}else{
										System.exit(-1);
								}
							}
						}
					}
				}
			}
	 }   
	 
	 /**
	  * Método principal
	  * @param args Argumentos recibidos por línea de comandos
	  * @exception Exception
	  */
	 public static void main(String[] args) throws Exception {
		 
		 //Creamos la interfaz gráfica del usuario final.
		 SudokuGUI aplicacion = new SudokuGUI();
		 aplicacion.init();	      
	 }		 
	}// Fin de la clase
