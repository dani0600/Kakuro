package domini.model;

public class Negra extends Cella {
	
	private int valorDreta;
	private int valorEsq;
	
	/**
	    * Constructora de cel.la Negra
	    *
	    */
	public Negra() 
	{
		super();
		this.valorDreta = -1;
		this.valorEsq = -1; 
	}
	
	/**
	    * Constructora de cel.la Negra amb els seus valors
	    *
	    * @param x posició X de taulell
	    * @param y posició Y de taulell
	    * @param vD valor de la dreta
	    * @param vE valor de l'esquerra
	    */
	public Negra(int x,int y,int vD,int vE) 
	{
		super(x,y);
		this.valorDreta = vD;
		this.valorEsq = vE;
	}

	
	/**
	    * Constructora de cel.la Negra amb els seus valors
	    *
	    * @param vD valor de la dreta
	    * @param vE valor de l'esquerra
	    */
	
	public Negra(int vD, int vE) 
	{
		super();
		this.valorDreta = vD;
		this.valorEsq = vE;
	}
	
	/**
	    * Es retorna el valor de la dreta de la cel.la
	    *
	    * @return <code>valorDreta</code>
	    */
	public int getValorDreta() {
		return valorDreta;
	}
	
	/**
	    * Es retorna el valor de l'esquerra de la cel.la
	    *
	    * @return <code>valorEsq</code>
	    */
	public int getValorEsq()
	{
		return valorEsq;
	}

	/**
	    * Es retorna si el valor de la cel.la es valid
	    *
	    * @return <code>true</code> Si es valid
	    * <code>false</code> Si no ho és
	    */
	public boolean validNegra()
	{
		if (this.valorDreta != -1 || this.valorEsq != -1) return true;
		return false;
	}
	
	/**
	    * Es modifica si el valor de la dreta de la cel.la
	    *
	    *@param x nou valor
	    */
	public void setValorDreta(int x) 
	{
		valorDreta = x;
	}
	
	/**
	    * Es modifica si el valor de l'esquerra de la cel·la
	    *
	    *@param x nou valor
	    */
	
	
	public void setValorEsq(int x)
	{
		valorEsq = x;
	}

	/**
	    * Es retorna si la cel·la és Negra
	    *
	    * @return <code>true</code> 
	    */
	public boolean esNegra()
	{
		return true;
	}
}