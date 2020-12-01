package domini.model;

public class Cella {

	protected int posX;
	protected int posY;
	
	
	/**
	    * Constructor cel.la
	    *
	    */
	public Cella() {}

	
	/**
	    * Constructor cel.la amb la seva posicio
	    * @param pX posicio X
	    * @param pY posicio Y
	    * 
	    */
	public Cella(int pX, int pY){
		this.posX = pX;
		this.posY = pY;
	}
	
	/**
	    * Es retorna la posicio X de la cel la
	    *
	    * @return <code>posX</code> 
	    * 
	    */
	public int getPosX() {
		return posX;
	}
	
	/**
	    * Es retorna la posicio Y de la cel la
	    *
	    * @return <code>posY</code> 
	    */
	public int getPosY() {
		return posY;
	}
	
	/**
	    * Es canvia la posicio X de la cel la
	    *
	    * @param x el nou valor de la posicio X 
	    * @return <code>true</code> 
	    */

	public boolean setPosX(int x) {
		posX = x;
		return true;
	}

	/**
	    * Es canvia la posicio Y de la cel.la
	    * @param x el nou valor de la posicio Y 
	    * @return <code>true</code> 
	    */
	public boolean setPosY(int x) {
		posY = x;
		return true;
	}
	
	/**
	    * Ens diu que la cel.la  no es negra
	    * @return <code>false</code> 
	    */
	
	public boolean esNegra() {
		return false;
	}

	
}
