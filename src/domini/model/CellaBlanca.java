package domini.model;

import java.util.*;

public class CellaBlanca extends Cella {
	
	private boolean[] possibles_valors;
	private int valor;
	
	
	/**
	    * Constructor de Cel.la Blanca 
	    * 
	    */
	
	public CellaBlanca() {
		super();
		this.valor = -1;
		boolean[] v = new boolean[10];
		Arrays.fill(v, false);
		possibles_valors = v;
		possibles_valors[0] = true;
	}
	
	/**
	    * Constructor de Cel.la Blanca amb tots els seus valors
	    * 
	    * @param value valor de la cel·la blanca
	    * @param x posició X de la cel·la
	    * @param y posició Y de la cel·la
	    */
public CellaBlanca(int value, int x, int y) {
		super(x, y);
		this.valor = value;
		boolean[] v = new boolean[10];
		Arrays.fill(v, false);
		possibles_valors = v;
		possibles_valors[0] = true;
}
	
	/**
	    * Ens retorna tots els possibles valors de la cel.la blanca
	    * @return <code>possibles_valors</code> 
	    */
	public boolean[] getPossiblesValors(){
		return possibles_valors;
	}
	
	/**
	    * Ens retorna el valor de la cel.la blanca
	    * @return <code>valor</code> 
	    */

	public int getValorBlanca() {
		return valor;
	}
	
	


	/**
	    * Es canvia el valor de la cel.la
	    *
	    * 
	    * @param x el nou valor de la cel.la
	    * @return <code>true</code> si estava buit
	    *  	<code>false</code> si hi havia un altre valor anteriorment
	    */


	public boolean setBlanca(int x){
		if(x == -1) {
			valor = x;
			return true;
		}
		else {
			if (validarNum(x)) {
				valor = x;
				return true;
			}
		}
		return false;
	}

	
	/**
	    * Es valida el valor de la cel.la
	    *
	    * @param x el valor que es vol validar
	    * @return <code>true</code> si es valid
	    *  	<code>false</code> si no es valid
	    */

	public boolean validarNum(int x) {
		return !possibles_valors[x];
	}

	/**
	    * Ens diu que la cel·la no es negra
	    *
	    * @return <code>false</code>
	    */
	
	public boolean esNegra() {
		return false;
	}
	
	public void resetValors() {
		Arrays.fill(this.possibles_valors, false);
		this.valor = -1;
	}
	
    
	/**
	    * Es canvia el valor v en el vector possibles_vectors a disponible
	    *
	    * @param v valor del vector possibles_vectors
	    */
	
    public void valorDisponible(int v){
		possibles_valors[v] = false;
	}
		
    /**
	    * Es canvia el valor v en el vector possibles_vectors a no disponible
	    *
	    * @param v valor del vector possibles_vectors
	    */

	public void valorNoDisponible(int v){
		possibles_valors[v] = true;
	}
	
	
}
