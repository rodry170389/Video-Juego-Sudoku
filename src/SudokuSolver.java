import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Clase SudokuSolver: Clase que resuelve el sudoku,
 * que inicializa el tablero, comprueba si son válidos 
 * los valores que pasamos al inicicalizar el tablero,
 * lo resuelve con el metodo principal solve() 
 * y finalmente nos imprime el tablero en línea de comandos.
 * @author José Rodrigo Fuentes Ramírez
 * @version	1.0 
 * @see SudokuGUI
 */

	public class SudokuSolver{
		
		/** Almacena los valores correctos, es decir, una vez ya resuelto el sudoku.*/
		public static String[][] paintr = new String[9][9];
		// public static String paintr = "valor"
		
		/** Almacena los valores de las 81 casillas del sudoku.*/
		private int[][] sudoku;
		// private int sudoku = números enteros
    
		/** Almacena el valor de las casillas.*/
		private char[][]davalor = new char[9][9];
		// private char davalor = inicial.charAt(2);
    
		/**
	     * Constructor del tablero dados sus filas, columnas y el
	     * valor, todo eso mediante un array de String bidimensional.
	     * @param inicial  String[][] inicial
	     * @exception Exception
	     */
		public SudokuSolver(String[][] inicial) throws Exception {
			
			// Define el tamaño del tablero.
			sudoku = new int[9][9];
		
			// Comprueba los valores que le pasamos y inicializa el atributo llamado sudoku
			if(inicial!=null) {
				for(int i=0; i<inicial.length; i++) {
					for(int j = 0; j<inicial.length;j++){
						
						//Inicializamos da davalor
						davalor[i][j]=inicial[i][j].charAt(2);
						
						//Comprueba el valor e inicializa el tablero.
						if(davalor[i][j]=='0'){
							sudoku[i][j]=0;
						}else{
							int row = Integer.parseInt(inicial[i][j].substring(0, 1));
							int col = Integer.parseInt(inicial[i][j].substring(1, 2));
							int val = Integer.parseInt(inicial[i][j].substring(2, 3));
							
							// Comprueba que el valor sea válido
							if(isValid(row, col, val) == true){
								sudoku[row][col] = val;
							}else{
								
								// Creamos una ventana para informarnos del error.
								JFrame ErrorB = new JFrame("ERROR!!!!");
								ErrorB.setResizable(false);
								Container ErrorBb = ErrorB.getContentPane();
								JTextArea textArea = new JTextArea(5, 10);
								textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
								textArea.setText(
										"ERROR al introducir un número!       "+
										"Por favor compruebe que no haya el mismo valor en la misma fila, "+
										"ni en la misma columna, ni en los 9 cuadros delimitados."
								);
								textArea.setBackground(Color.ORANGE);
								textArea.setLineWrap(true);
								textArea.setWrapStyleWord(true);
								textArea.setEditable(false);
								ErrorBb.add(textArea);
								ErrorB.setSize(240,150);
								ErrorB.setLocationRelativeTo(null);
								ErrorB.setVisible(true);
								throw new Exception("Esta en la misma fila, columna o cuadrado de 9 ("+inicial[i][j]+")");
							}    	
						}
					}
				}
			}
		} 
		
		/**
		 * Método que muestra el tablero en la consola.
		 * Tambien inicializa paintr. 
		 * No se le pasa ningún parametro.
		 */	
		public void display() {
			
			// Inicializa paintr y muestra el tablero en la consola.
			System.out.println(" 012345678");
    		for(int i = 0; i < sudoku.length; i++){
				System.out.print(i);
				for(int j = 0; j < sudoku.length; j++){
					paintr[i][j]= String.valueOf(sudoku[i][j]);
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
		}
		
		/**
		 * Método que valida los valores con los que
		 * inicializamos el sudoku. 
		 * @param row las filas
		 * @param col las columnas
		 * @param val el valor
		 * @return aux Devuelve true si es válido y false si no lo es.
		 */	
		public boolean isValid(int row, int col, int val) {
			
			//Variable interna que guarda valores para luego ser comprobados.
			int [] com = new int[9];
			
			//Variable que devuelve.
			boolean aux = true;
			
			// Valida las filas y las columnas.
			for(int i = 0; i < sudoku.length; i++){
				if(sudoku[row][i] == val | sudoku[i][col] == val){
					aux = false;
				}
			}
			
			// Valida en la Region 1
			if(row < 3 && col < 3){
				for(int j=0; j<3; j++){
					for(int k=0; k<3; k++){
						com[0] = sudoku[j][k];
						if(com[0] == val){
							aux = false;      
						}
					}
				}
			}  
			
			// Valida la Region 2 	  
			if(row < 3 && (col>2 && col<6)){
				for(int i = 0; i < 3; i++){
					for(int j = 3; j < 6; j++){
						com[1] = sudoku[i][j];
						if(com[1] == val){
							aux = false; 
						}
					}
				}
			}
			
			// Valida la Region 3	   
			if(row < 3 && (col>5 && col<9)){
				for(int i = 0; i < 3; i++){
					for(int j = 6; j < 9; j++){
						com[2] = sudoku[i][j];
						if(com[2] == val){
							aux = false; 
						}
					}
				}
			}
			
			// Valida la Region 4 	   
			if((row>2 && row<6) && col<3){
				for(int i = 3; i < 6; i++){
					for(int j = 0; j < 3; j++){
						com[3] = sudoku[i][j];
						if(com[3] == val){
							aux = false; 
						}
					}
				}	   
			}
   
			// Valida la Region 5 	   
			if((row>2 && row<6)&& (col>2 && col<6)){
				for(int i = 3; i < 6; i++){
					for(int j = 3; j < 6; j++){
						com[4] = sudoku[i][j];
						if(com[4] == val){
							aux = false; 
						}
					}
				}	   
			}
			
			// Valida la Region 6 	   
			if((row>2 && row<6) && (col>5 && col<9)){
				for(int i = 3; i < 6; i++){
					for(int j = 6; j < 9; j++){
						com[5] = sudoku[i][j];
						if(com[5] == val){
							aux = false; 
						}
					}
				}	   
			}
			
			// Valida la Region 7 	   
			if((row>5 && row<9) && col<3){
				for(int i = 6; i < 9; i++){
					for(int j = 0; j < 3; j++){
						com[6] = sudoku[i][j];
						if(com[6] == val){
							aux = false; 
						}
					}
				}	   
       	  	}
			
			// Valida la Region 8 	   
			if((row>5 && row<9)&& (col>2 && col<6)){
				for(int i = 6; i < 9; i++){
					for(int j = 3; j < 6; j++){
						com[7] = sudoku[i][j];
						if(com[7] == val){
							aux = false; 
						}
					}
				}	   
			}	
       
			// Valida la Region 9 	   
			if((row>5 && row<9) && (col>5 && col<9)){
				for(int i = 6; i < 9; i++){
					for(int j = 6; j < 9; j++){
						com[8] = sudoku[i][j];
						if(com[8] == val){
							aux = false; 
						}
					}
				}	   
       	  	}	
        	
			//Devuelve aux 
			return aux;
		} 
		
		/**
		 * Método que retorna la solución del
		 * sudoku
		 * @return solve(0,0) Devuelve la solución.
		 */	
		public boolean solve() {
			return solve(0, 0);
		}

		/**
		 * Método que encuentra la solución del
		 * sudoku recorriendo cada casilla.
		 * @param row las filas.
		 * @param col las columnas.
		 * @return true si encuentra solución y false si no la encuentra.
		 */	
		public boolean solve(int row, int col) {
			
			// Cuando llega al final del tablero.
			if(row == 9 & col == 0){
    		  return true;
			}
			
			// Si los valores no son igual a cero, soluciona la siguiente casilla
			if(sudoku[row][col] != 0){
				if(col < 8){
					if(solve(row,col+1)){
						//Soluciona
						return true;
					}else{
						return false;
					}
				}else{// si es igual a 8
					if(solve(row+1,0)){
						//Soluciona
						return true;
					}else{
						return false;
					}
				}
			}else{
				for(int x = 1; x < 10; x++){
					//Asigna el valor, si es válido
					if(isValid(row,col,x) == true){
						sudoku[row][col] = x;
						if(col < 8){
							if(solve(row,col+1)){
								//Soluciona
								return true;
							}
						}else{// si es igual a 8
							if(solve(row+1,0)){
								//Soluciona
								return true;
							}
						}
					}
				}
				sudoku[row][col]=0;
				return false;
			}
		}
	}// Fin de la clase
