import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Clase SudokuSolverMini: Clase que resuelve el sudoku-Kids,
 * que inicializa el tablero, comprueba si son v�lidos 
 * los valores que pasamos al inicicalizar el tablero 
 * y finalmente nos imprime el tablero.
 * @author José Rodrigo Fuentes Ramírez
 * @version	1.0  
 * @see SudokuGUIMini
 */

public class SudokuSolverMini{
	
	/** Almacena los valores correctos, es decir, una vez ya resuelto el sudoku-Kids.*/
    public static String[][] muestra = new String[6][6];
    // public static String muestra = "valor"
	
    /** Almacena los valores de las 36 casillas del sudoku-Kids.*/
    private int[][] sudokumini;
    // private int sudoku = números enteros
    
    /** Almacena el valor de las casillas del sudoku-Kids.*/
    private char[][]davalormini = new char[6][6];
    // private char davalor = inicialmini.charAt(2);
    
    /**
     * Constructor del tablero dados sus filas, columnas y el
     * valor, todo eso mediante un array de String bidimensional.
     * @param inicialmini  String[][] inicialmini
     * @exception Exception
     */
    public SudokuSolverMini(String[][] inicialmini) throws Exception {
     
    	// Define el tamaño del tablero.
    	sudokumini = new int[6][6];
    	
    	// Comprueba los valores que le pasamos y inicializa el atributo llamado sudokumini
    	if(inicialmini!=null) {
    		for(int i=0; i<inicialmini.length; i++) {
    			for(int j = 0; j<inicialmini.length;j++){
    				
    				//Inicializamos da davalor
    				davalormini[i][j]=inicialmini[i][j].charAt(2);
    				
    				//Comprueba el valor e inicializa el tablero Sudoku-Kids.
    				if(davalormini[i][j]=='0'){
    					sudokumini[i][j]=0;
    				}else{
    					int rowmini = Integer.parseInt(inicialmini[i][j].substring(0, 1));
    					int colmini = Integer.parseInt(inicialmini[i][j].substring(1, 2));
    					int valmini = Integer.parseInt(inicialmini[i][j].substring(2, 3));
    					
    					// Comprueba que el valor sea válido
    					if(isValidmini(rowmini, colmini, valmini) == true){
    						sudokumini[rowmini][colmini] = valmini;
    					}else{ 
    						
    						// Creamos una ventana para informarnos del error.
    						JFrame ErrorB = new JFrame("ERROR!!!!");
							ErrorB.setResizable(false);
							Container ErrorBb = ErrorB.getContentPane();
							JTextArea textArea = new JTextArea(5, 10);
							textArea.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
							textArea.setText(
									"ERROR al introducir un número!!!!     "+
									"Por favor compruebe que no haya el mismo valor en la misma fila, "+
									"ni en la misma columna, ni en los 6 cuadros delimitados."
							);
							textArea.setBackground(Color.ORANGE);
							textArea.setLineWrap(true);
							textArea.setWrapStyleWord(true);
							textArea.setEditable(false);
							ErrorBb.add(textArea);
							ErrorB.setSize(240,150);
							ErrorB.setLocationRelativeTo(null);
							ErrorB.setVisible(true);
							throw new Exception("Esta en la misma fila, columna o cuadrado de 9 ("+inicialmini[i][j]+")");
    					}
    				}
    			}
    		}
    	}
    } 
  
    /**
	 * Método que muestra el tablero en la consola.
	 * Tambien inicializa muestra. 
	 * No se le pasa ningún parametro.
	 */	
    public void displaymini() {
    
    	// Inicializa muestra e imprime el tablero en la consola.
    	System.out.println(" 012345");
    	for(int i = 0; i < sudokumini.length; i++){
    		System.out.print(i);
    		for(int j = 0; j < sudokumini.length; j++){
    			muestra[i][j]= String.valueOf(sudokumini[i][j]);
    			System.out.print(sudokumini[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    /**
	 * Método que válida los valores con los que
	 * inicializamos el sudoku-Kids. 
	 * @param rowmini las filas
	 * @param colmini las columnas
	 * @param valmini el valor
	 * @return auxmini Devuelve true si es válido y false si no lo es.
	 */	
    public boolean isValidmini(int rowmini, int colmini, int valmini) {
    	
      //Variable interna que guarda valores para luego ser comprobados.
      int [] comnino = new int[6];
      
      //Variable que devuelve.
      boolean auxmini = true;
      
      // Válida las filas y las columnas.
      for(int i = 0; i < sudokumini.length; i++){
    	  if(sudokumini[rowmini][i] == valmini | sudokumini[i][colmini] == valmini){
    		  auxmini = false;
    	   }
      }
     
      // Valida en la Region 1
      if(rowmini < 2 && colmini < 3){
    	  for(int j=0; j<2; j++){
			  for(int k=0; k<3; k++){
				  comnino[0] = sudokumini[j][k];
				  if(comnino[0] == valmini){
					  auxmini = false;      
				  }
			  }
		  }
      }  
      
      // Valida en la Region 2
      if(rowmini < 2 && (colmini>2 && colmini<6)){
    	  for(int i = 0; i < 2; i++){
    		  for(int j = 3; j < 6; j++){
    			  comnino[1] = sudokumini[i][j];
    			  if(comnino[1] == valmini){
    				  auxmini = false; 
    			  }
    		  }
    	  }
      }
      
      // Valida en la Region 3
      if((rowmini>1 && rowmini<4) && colmini<3){
    	  for(int i = 2; i < 4; i++){
    		  for(int j = 0; j < 3; j++){
    			  comnino[2] = sudokumini[i][j];
    			  if(comnino[2] == valmini){
    				  auxmini = false; 
    			  }
    		  }
    	  }	   
      }
      
      // Valida en la Region 4
      if((rowmini > 1&& rowmini<4)&&(colmini>2 && colmini<6)){
    	  for(int i = 2; i < 4; i++){
    		  for(int j = 3; j < 6; j++){
    			  comnino[3] = sudokumini[i][j];
    			  if(comnino[3] == valmini){
    				  auxmini = false; 
    			  }
    		  }
    	  }
      }
      
      // Valida en la Region 5
      if((rowmini>3 && rowmini<6) && colmini<3){
    	  for(int i = 4; i < 6; i++){
    		  for(int j = 0; j < 3; j++){
    			  comnino[4] = sudokumini[i][j];
    			  if(comnino[4] == valmini){
    				  auxmini = false; 
    			  }
    		  }
    	  }
      }
      
      // Valida en la Region 6
      if((rowmini>3 && rowmini<6)&&(colmini>2 && colmini<6)){
    	  for(int i = 4; i < 6; i++){
    		  for(int j = 3; j < 6; j++){
    			  comnino[5] = sudokumini[i][j];
    			  if(comnino[5] == valmini){
    				  auxmini = false; 
    			  }
    		  }
    	  }
      }   
      
      //Devuelve auxmini 
      return auxmini;
   
    } 
    
    /**
	 * Método que retorna la solución del
	 * sudoku-Kids
	 * @return solve(0,0) Devuelve la solución.
	 */	
    public boolean solvemini() {
    	return solvemini(0, 0);
    }
    
    /**
	 * Método que encuentra la solución del
	 * sudoku-Kids recorriendo cada casilla.
	 * @param rowmini las filas.
	 * @param colmini las columnas.
	 * @return true si encuentra solución y false si no la encuentra.
	 */	
    public boolean solvemini(int rowmini, int colmini) {
    	
    	// Cuando llega al final del tablero.
    	if(rowmini == 6 & colmini == 0){
    		return true;
    	}
    	
    	// Si los valores no son igual a cero, soluciona la siguiente casilla
    	if(sudokumini[rowmini][colmini] != 0){
    		if(colmini < 5){
    			if(solvemini(rowmini,colmini+1)){
    				//Soluciona
    				return true;
    			}else{
    				return false;
    			}
    		}else{// si es igual a 5   			
    			if(solvemini(rowmini+1,0)){
    				//Soluciona
    				return true;
    			}else{
    				return false;
    			}
    		}
    	}else{
    		for(int x = 1; x < 7; x++){
    			//Asigna el valor, si es válido
    			if(isValidmini(rowmini,colmini,x) == true){
    				sudokumini[rowmini][colmini] = x;
    				if(colmini < 5){
    					if(solvemini(rowmini,colmini+1)){
    						//Soluciona
    						return true;
    					}
    				}else{// si es igual a 5
    					if(solvemini(rowmini+1,0)){
    						//Soluciona
    						return true;
    					}
    				}
    			}
    		}
    		sudokumini[rowmini][colmini]=0;
    		return false;
    	}   
      }     
	}// Fin de la clase
