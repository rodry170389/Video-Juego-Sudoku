import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

/**
 * Clase SudokuGUIMini: Clase que crea la interfaz de
 * usuario Sudoku-Kids, implementa la intefaz ActionListner para
 * los eventos de cada boton creado y define métodos
 * para unir la interfaz gráfica con la clase que
 * resuelve el sudoku.
 * @author José Rodrigo Fuentes Ramírez
 * @version	1.0 
 * @see SudokuSolverMini
 * @see SudokuGUI
 */

public class SudokuGUIMini implements ActionListener{

	/**
	   * Atributos que configuran la interfaz que interactuará con
	   * el usuario final.
	   */
	
	/** Define la ventana llamada "Sudoku-Kids"*/
	private JFrame framemini;
	
	/** Contenedor que contiene todo de SudokuMini*/
	private Container contenedormini;
	
	/** Nombres de los 3 botones de la interfaz SudokuMini*/
	private final String nombresmini[] = {"Resolver", "Limpiar", "Aleatorio"};
	
	/** Botones para la interfaz gráfica "Sudoku-Kids"*/
	private JButton comandosmini[]= new JButton[3];
	// private JButton comandosmini = new JButton(nombresmini).
	
	/** Areas de texto para las 36 casillas del sudokuMini*/
	private JTextArea textomini[][] = new JTextArea[6][6];
	// private JTextArea textomini = new JTextArea().
	
	/** Limitadores de caracteres para cada area de texto de la interaz SudokuGUIMini*/
	private LimitedStyledDocument lpd[][] = new LimitedStyledDocument[6][6];
	// private LymitedStyledDocument lpd = new LimitedStyledDocument(SudokuGUI.MAX_CHARACTERS).
	
	/** Configura el titulo de la Interfaz gráfica SudokuGUIMini*/
	private JLabel titulomini;
	
	/** Creamos 6 paneles para contener las 31 casillas de las 6 regiones*/
	private JPanel paneles6grandes [] = new JPanel[6];
	// private JPanel paneles6grandes = new Jpanel(new GridLayout(2,3,4,4))
	
	/** Contiene las 6 regiones de 6 casillas de texto*/
	private JPanel sudokuminis;
	
	/** Contiene los 3 botones de la interfaz gráfica SudokuGUIMini*/
	private JPanel panelsur;
	
	/**
	  * Método que crea la interfaz de usuario SudokuGUIMini. 
	  * No se le pasa ningún parametro.
	  * Se importan todos los métodos.
	  */
	public void initmini(){
	  
		// Modificamos la apariencia y la funcionalidad de la ventana. 
		framemini = new JFrame("SudoKu-Kids");
        framemini.setResizable(false);
        
        // Convertimos a la ventana framemini en un contenedor
        contenedormini = framemini.getContentPane();
        
        // Modificamos el titulo de la interfaz de usuario SudokuGUIMini    
        titulomini = new JLabel("SudoKu-Kids",JLabel.CENTER);
        titulomini.setFont(new Font( "Brush Script MT", Font.ITALIC, 40));
        titulomini.setForeground(Color.WHITE);
        
        // Añadimos al panel sudoku una tabla de 6 (Gridlayout) y le damos un color.
        sudokuminis = new JPanel(new GridLayout(3,2,4,4));
        sudokuminis.setBackground(Color.RED);
        
        // Añadimos a 6 paneles 6 tablas(GridLayout), estas serán cada una de las casillas del sudoku-Kids. 
        for(int i = 0; i < paneles6grandes.length; i++){
               	paneles6grandes[i] = new JPanel(new GridLayout(2,3,1,1));
            	paneles6grandes[i].setBackground(Color.DARK_GRAY);
            }
      
        // Inicializamos las areas de texto de los cuadritos pequeños y los limitamos a un caracter y cambiamos su apariencia.
        for(int i = 0; i < textomini.length; i++){
        	for(int j = 0; j < textomini.length; j++){
        		lpd[i][j] = new LimitedStyledDocument(SudokuGUI.MAX_CHARACTERS);
        		textomini[i][j] = new JTextArea();
        		textomini[i][j].setBackground(Color.ORANGE);
        		textomini[i][j].setDocument(lpd[i][j]);
        		textomini[i][j].setFont(new Font("Goudy Stout", Font.ITALIC, 35));
        	}
        	
        }
        
        // Añadimos los paneles pequeños(representan a cada cuadrito) a las 6 regiones(reprentan a los 6 cuadros grandes).
        for(int i = 0; i < textomini.length; i++){
        	for(int j = 0; j < textomini.length; j++){
        	paneles6grandes[i].add(textomini[i][j]);
        	sudokuminis.add(paneles6grandes[i]);
        	}
        }
        
        // Añadimos a panelsur un GridLayout y le damos color.
        panelsur = new JPanel(new GridLayout(1,3,5,0));
        panelsur.setBackground(Color.RED);       
      
        //Damos nombres a los botones, los añadimos a su panel correspondiente 
        // y lo mas importante, los asociamos con la interfaz ActionListener.
        for(int j = 0; j < comandosmini.length; j++){
        	comandosmini[j] = new JButton(nombresmini[j]);
        	panelsur.add(comandosmini[j]);
        	comandosmini[j].addActionListener(this);
        	comandosmini[j].setBackground(Color.WHITE);
        }
        
        // Agregamos los respectivos objetos al panel contenedormini
        contenedormini.setLayout(new BorderLayout());
        contenedormini.add(panelsur,BorderLayout.SOUTH); 
        contenedormini.add(titulomini,BorderLayout.NORTH);
        contenedormini.add(sudokuminis, BorderLayout.CENTER);
        contenedormini.setBackground(Color.RED);
       
        // Damos el tamaño a la ventana y el lugar, finalmente lo hacemos visible.
        framemini.setSize(280,350);
        framemini.setLocationRelativeTo(null);
        framemini.setVisible(true);
	}
	 
	/**
	  * Método que obtiene los números ingresados en la interfaz SudokuGUIMini,
	  * cambia a las correctas coordenadas del tablero y los almacena
	  * en un array bidimencional para luego devolverlo al constructor
	  * de SudokuSolverMini. 
	  * Devuelve un array de String bidimensional.
	  * @return arrayauxmini guarda las coodenadas del tablero y el valor. 
	  */
	public String[][]GetValoresmini(){
		
		// Guarda las coordenadas correctas
		String coordmini[][]={
				{textomini[0][0].getText(),textomini[0][1].getText(),textomini[0][2].getText(),textomini[1][0].getText(),textomini[1][1].getText(),textomini[1][2].getText(),},
				{textomini[0][3].getText(),textomini[0][4].getText(),textomini[0][5].getText(),textomini[1][3].getText(),textomini[1][4].getText(),textomini[1][5].getText(),},
				{textomini[2][0].getText(),textomini[2][1].getText(),textomini[2][2].getText(),textomini[3][0].getText(),textomini[3][1].getText(),textomini[3][2].getText(),},
				{textomini[2][3].getText(),textomini[2][4].getText(),textomini[2][5].getText(),textomini[3][3].getText(),textomini[3][4].getText(),textomini[3][5].getText(),},
				{textomini[4][0].getText(),textomini[4][1].getText(),textomini[4][2].getText(),textomini[5][0].getText(),textomini[5][1].getText(),textomini[5][2].getText(),},
				{textomini[4][3].getText(),textomini[4][4].getText(),textomini[4][5].getText(),textomini[5][3].getText(),textomini[5][4].getText(),textomini[5][5].getText(),}
		};
		
		// Guarda la posición en la que se encuentra el valor, y el valor en si mismo para luego devolverlo.
		String [][]arrayauxmini = new String [6][6];
		
	    // Comprobamos y guardamos las coordenadas en un array: arrayauxmini
		uno: for(int i = 0; i < coordmini.length; i++){
			for(int j = 0; j < coordmini.length; j++){
				if(coordmini[i][j].equals("1")||coordmini[i][j].equals("2")||coordmini[i][j].equals("3")
						||coordmini[i][j].equals("4")||coordmini[i][j].equals("5")||coordmini[i][j].equals("6")){
					arrayauxmini[i][j] = String.valueOf(i)+String.valueOf(j)+ coordmini[i][j];
				}else{
					if(coordmini[i][j].equals("")){
						arrayauxmini[i][j] = String.valueOf(i)+String.valueOf(j)+"0";
					}else{
						 // Creamos una ventana para informar del error(car�cter no v�lido)
		 				 JFrame Ayuda = new JFrame("ERROR!!!!");
		 				 Ayuda.setResizable(false);
		 				 Container VenAyuda = Ayuda.getContentPane();
		 				 JTextArea textArea = new JTextArea(5, 10);
		 				 textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
		 				 textArea.setText("Error!! al introducir un carácter. "+
		 						 		  "Usted esta intentando introducir un simbolo "+
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
		 
		return arrayauxmini;
	}
	
	/**
	  * Método que muestra el resultado final en la interfaz gráfica SudokuGUIMini y 
	  * no deja que editemos una vez mostrado el resultado.
	  * No recibe ningún parámetro.
	  */ 
	public void Resultadofinalmini(){
		
		// Copiamos en array(finmini) la solución del sudoku-Kids propuesto
		String[][] finmini = SudokuSolverMini.muestra;
		
		// Cambiamos las coordenadas para que lo muestre correctamente.
		String[][]pantallamini ={
				{finmini[0][0],finmini[0][1],finmini[0][2],finmini[1][0],finmini[1][1],finmini[1][2],},
				{finmini[0][3],finmini[0][4],finmini[0][5],finmini[1][3],finmini[1][4],finmini[1][5],},
				{finmini[2][0],finmini[2][1],finmini[2][2],finmini[3][0],finmini[3][1],finmini[3][2],},
				{finmini[2][3],finmini[2][4],finmini[2][5],finmini[3][3],finmini[3][4],finmini[3][5],},
				{finmini[4][0],finmini[4][1],finmini[4][2],finmini[5][0],finmini[5][1],finmini[5][2],},
				{finmini[4][3],finmini[4][4],finmini[4][5],finmini[5][3],finmini[5][4],finmini[5][5],}
		};
		
		// Mostramos el resultado y no dejamos que se edite.
		for(int i =0; i<textomini.length; i++){
			for(int j=0; j<textomini.length;j++){
				textomini[i][j].setText(pantallamini[i][j]);
				textomini[i][j].setEditable(false);
			}
		}
	}
	 
	/**
	  * Método para dar funcionalidad al boton "Resolver".
	  * Resuelve el sudoku que queremos que imaginemos.
	  * No recibe ningún parámetro.
	  * @exception Exception
	  */
	public void BotonResolvermini() throws Exception{
		
		// Pasamos los valores con el metodo(GetValoresmini)
		SudokuSolverMini s;
		s = new SudokuSolverMini(GetValoresmini());
		
		// Imprimimos en la consola los valores que le pasamos
		s.displaymini();
		
		// Resuelve e imprime el resultado en la consola y en la interfaz gráfica Sudoku-Kids. 
		if(s.solvemini()) {
			s.displaymini();
			Resultadofinalmini();		
			}else{
        System.out.println("Intentelo otra vez por favor!!");
			}
	}

	/**
	  * Método para dar funcionalidad al boton "Limpiar".
	  * Modifica el texto que esta en la interfaz gráfica
	  * SudokuGUIMini y lo hace editable.
	  * No recibe ningún parámetro.
	  */   
	public void BotonLimpiarmini(){
	
		//Borra todos los números y hace la interfaz grafica SudokuGUIMini editable.
		for(int i =0; i<textomini.length; i++){
			for(int j=0; j<textomini.length;j++){
				textomini[i][j].setText("");
				textomini[i][j].setEditable(true);
			}
		}
	}
	
	/**
	  * Método para dar funcionalidad al boton "Aleatorio".
	  * Asigna números aleatoriamente en el tablero, para
	  * luego resolverlo.
	  * No recibe ningún parámetro.
	  */   
	 public void BotonAleatoriomini(){
		 
		 // Definimos las variables que guardan los numeros aleatorios.
		 int numero;
		 int numero2;
		 int numero3;
		 
		 // Variable que necesitamos para guardar la variable numero.
		 int guardavalor = 0;
		 
		 // Generamos las filas y las columnas aleatoriamente y luego lo escribimos en la interfaz. 
		 Random rnd = new Random(System.currentTimeMillis());
		 for(int i = 0; i < 6; i++){
			 	 numero = rnd.nextInt(6);
				 numero2 = rnd.nextInt(6);
				 numero3 = guardavalor;
				 numero3 = numero3+1;
				 guardavalor = numero3;
				 textomini[numero][numero2].setText(String.valueOf(numero3));		 
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
	public void actionPerformed(ActionEvent mis) {
		
		// Elige el método según el botón que pulse el usuario en la interfaz gráfica SudokuGUIMini. 
		if (mis.getActionCommand().equals("Resolver")){
			try {
				BotonResolvermini();
			} catch (Exception e) {
				e.printStackTrace();
			}	
			}else{
				if(mis.getActionCommand().equals("Aleatorio")){
					BotonLimpiarmini();
					BotonAleatoriomini();
				}else{
					BotonLimpiarmini();
				}
			}
	}
	}// Final clase
				




