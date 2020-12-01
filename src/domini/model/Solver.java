package domini.model;

public class Solver {
	
	

	/**
	    * Constructora de Solver
	    * 
	    */
	public Solver(){
		
	}
	
	
	
	/**
	    * Ens determina si el taulell del Kakuro te solucio o no.
	    * <p>
	    * Aquesta funcio revisa, per a cada cella, si pot tenir un valor valid per a tot el taulell.
	    * Si es aixi, llavors omple el taulell i soluciona el Kakuro.
	    * <p>
	    * @param T Array de cel.les.
	    * @param row Posicio actual en la fila.
	    * @param col Posicio actual en la columna.
	    * @param mode mode de resolucio del taulell.
	    * @return <code>true</code> si te solucio.
	    *  	<code>false</code> si no te solucio.
	    */

	public boolean solve(Cella[][] T, int row, int col, int mode) {
		
		if (row == T.length) {
			return true;
		}
		
		Cella[] c = T[0];
		if (col == c.length) {
			if(mode == 1)return solve(T, row + 1, 0, 1);
			else return solve(T, row+1, 0, 2);
		}
		
		if(T[row][col].esNegra()) {
			if(mode == 1)return solve(T, row, col+1,1);
			else return solve(T, row, col+1,2);
		}
		if(mode == 1) {
			boolean[] b = ((CellaBlanca)T[row][col]).getPossiblesValors();
			for(int i = 1; i <= 9; i++) {	
				if(!b[i]) {
					if(isValidRow(T,row,col,i) && isValidCol(T,row,col,i)) {
						((CellaBlanca)T[row][col]).setBlanca(i);
						avisaCeldasAdyacentes(T, row, col, i);
						if(solve(T, row, col + 1, 1)) return true;
						else desavisaCeldasAdyacentes(T, row, col, i);
					}
				}
			}
		}
		else if(mode == 2) {
			boolean[] b = ((CellaBlanca)T[row][col]).getPossiblesValors();
			for(int i = 9; i >= 1; i--) {	
				if(!b[i]) {
					if(isValidRow(T,row,col,i) && isValidCol(T,row,col,i)) {
						((CellaBlanca)T[row][col]).setBlanca(i);
						avisaCeldasAdyacentes(T, row, col, i);
						if(solve(T, row, col + 1, 2)) return true;
						else desavisaCeldasAdyacentes(T, row, col, i);
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	    * Determina si el valor de la cel�la a la posicio (row,col) es valid per la seva fila
	    *
	    * @param T Array de cel.les.
	    * @param row Posicio actual en la fila.
	    * @param col Posicio actual en la columna.
	    * @param value el valor de la cella a revisar.
	    * @return <code>true</code> si es un valor valid
	    *  	<code>false</code> si no es altre valor valid
	    */
	
	public boolean isValidRow(Cella[][] T, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;

        for (int i = col - 1; i >= 0; i--) {
            if (T[row][i].esNegra()) {
                totalValue = ((Negra)T[row][i]).getValorDreta();
                break;
            }
            sum += ((CellaBlanca)T[row][i]).getValorBlanca();
        }

        if (sum > totalValue) {
            return false;
        }

        Cella[] c = T[0];
        if (col == c.length - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (T[row][col+1].esNegra()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
	
	/**
	    * Determina si el valor de la cel.la a la posicio (row,col) es valid per la seva columna
	    *
	    * @param T Array de cel.les.
	    * @param row Posicio actual en la fila.
	    * @param col Posicio actual en la columna.
	    * @param value el valor de la cel.la a revisar.
	    * @return <code>true</code> si es un valor valid
	    *  	<code>false</code> si no es altre valor valid
	    */
	public boolean isValidCol(Cella[][] T, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;

        for (int i = row - 1; i >= 0; i--) {
            if (T[i][col].esNegra()) {
                totalValue = ((Negra)T[i][col]).getValorEsq();
                break;
            }
            sum += ((CellaBlanca)T[i][col]).getValorBlanca();
        }

        if (sum > totalValue) {
            return false;
        }

        if (row == T.length - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (T[row+1][col].esNegra()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
	
	/**
	    * Funcio que avisa a totes les cel les que es veguin afectades per un nou valor afegit
	    *<p>
	    * El que fa es que aquest nou valor ja no sigui disponible en la mateixa fila i columna
	    *<p>
	    * @param taulell Array de cel.les.
	    * @param x Posicio de la fila dins del taulell
	    * @param y Posicio de la columna dins del taulell
	    * @param valor el nou valor que s'ha ficat al solver
	    * 
	    */
	
	public void avisaCeldasAdyacentes(Cella[][]taulell, int x, int y, int valor) 
	{
	
		int auxX = x;
		int auxY = y;
		//avisar filas
		while (auxX < taulell.length && !taulell[auxX][y].esNegra()) 
		{
			((CellaBlanca)taulell[auxX][y]).valorNoDisponible(valor);
			//taulell[auxX][y].setAcumFila(valor);
			++auxX;
		}
		auxX = x;
		while (auxX >= 0 && !taulell[auxX][y].esNegra())
		{
			((CellaBlanca)taulell[auxX][y]).valorNoDisponible(valor);
			//Taulell[auxX][y].setAcumFila(valor);
			--auxX;
		}
		//avisar Columnas
		while (auxY < taulell[x].length && !taulell[x][auxY].esNegra())
		{
			((CellaBlanca)taulell[x][auxY]).valorNoDisponible(valor);
			//Taulell[x][auxY].setAcumColumn(valor);
			++auxY;
		}
		auxY = y;
		while (auxY >= 0 && !taulell[x][auxY].esNegra())
		{
			((CellaBlanca)taulell[x][auxY]).valorNoDisponible(valor);
			//Taulell[x][auxY].setAcumColumn(valor);
			--auxY;
		}

	}
	
	/**
	    * Funcio que desavisa a totes les cel les que es veguin afectades per un valor afegit
	    *<p>
	    * El que fa es que aquest valor tornar a estar disponible en la mateixa fila i columna
	    *<p>
	    * @param taulell Array de cel.les.
	    * @param x Posicio de la fila dins del taulell
	    * @param y Posicio de la columna dins del taulell
	    * @param valor el valor que volem treure
	    * 
	    */
	public void desavisaCeldasAdyacentes(Cella[][] taulell, int x, int y, int valor) 
	{
		
		int auxX = x;
		int auxY = y;
		//avisar filas
		while (auxX < taulell.length && !taulell[auxX][y].esNegra()) 
		{
			((CellaBlanca)taulell[auxX][y]).valorDisponible(valor);
			++auxX;
		}
		auxX = x;
		while (auxX >= 0 && !taulell[auxX][y].esNegra())
		{
			((CellaBlanca)taulell[auxX][y]).valorDisponible(valor);
			--auxX;
		}
		//avisar Columnas
		while (auxY < taulell[x].length && !taulell[x][auxY].esNegra())
		{
			((CellaBlanca)taulell[x][auxY]).valorDisponible(valor);
			++auxY;
		}
		auxY = y;
		while (auxY >= 0 && !taulell[x][auxY].esNegra())
		{
			((CellaBlanca)taulell[x][auxY]).valorDisponible(valor);
			--auxY;
		}

	}
}